package com.deveteris.magazzino.services.impl;

import com.deveteris.magazzino.mapper.OrdiniMapper;
import com.deveteris.magazzino.mapper.PrevisioneFabbisognoMpMapper;
import com.deveteris.magazzino.model.*;
import com.deveteris.magazzino.exceptions.FornitoreNonTrovatoException;
import com.deveteris.magazzino.exceptions.OrdineNonTrovatoException;
import com.deveteris.magazzino.repository.OrdineRepository;
import com.deveteris.magazzino.repository.FornitoreRepository;
import com.deveteris.magazzino.repository.MateriaPrimaRepository;
import com.deveteris.magazzino.repository.PrevisioneFabbisognoMpRepository;
import com.deveteris.magazzino.requests.OrdineMateriaPrimaRequest;
import com.deveteris.magazzino.requests.OrdineRequest;
import com.deveteris.magazzino.response.ManipulateOrdineMateriePrimeResponse;
import com.deveteris.magazzino.services.OrdiniService;
import com.deveteris.magazzino.util.QuantitaMeseMp;
import dto.OrdineDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class OrdiniServiceImpl implements OrdiniService {

    private final OrdineRepository ordineRepository;
    private final FornitoreRepository fornitoreRepository;
    private final MateriaPrimaRepository materiaPrimaRepository;
    private final OrdiniMapper ordiniMapper;
    private final PrevisioneFabbisognoMpRepository previsioneFabbisognoMpRepository;
    private final PrevisioneFabbisognoMpMapper mapper;


    public OrdiniServiceImpl(OrdineRepository ordineRepository, FornitoreRepository fornitoreRepository, MateriaPrimaRepository materiaPrimaRepository, OrdiniMapper ordiniMapper, PrevisioneFabbisognoMpRepository previsioneFabbisognoMpRepository, PrevisioneFabbisognoMpMapper mapper) {
        this.ordineRepository = ordineRepository;
        this.fornitoreRepository = fornitoreRepository;
        this.materiaPrimaRepository = materiaPrimaRepository;
        this.ordiniMapper = ordiniMapper;
        this.previsioneFabbisognoMpRepository = previsioneFabbisognoMpRepository;
        this.mapper = mapper;
    }

    @Override
    @Transactional
    public OrdineDto persistOrdine(OrdineRequest ordine) {
        Ordine entity = Optional.ofNullable(ordine.getId())
                .map(idOrdine -> {
                    Ordine ordineEntity = ordineRepository.findById(idOrdine)
                            .orElseThrow(() -> new OrdineNonTrovatoException("Ordine con Id {} non trovato", ordine.getId()));
                    Optional.ofNullable(ordine.getDataOrdine()).ifPresent(ordineEntity::setDataOrdine);
                    Optional.ofNullable(ordine.getDataConsegna()).ifPresent(ordineEntity::setDataOrdine);
                    return ordineRepository.save(ordineEntity);
                })
                .orElseGet(() -> {
                    Ordine ordineToSave = new Ordine();
                    ordineToSave.setDataOrdine(ordine.getDataOrdine());
                    ordineToSave.setDataConsegna(ordine.getDataConsegna());
                    return ordineRepository.save(ordineToSave);
                });
        return ordiniMapper.getOrdineDtoFromEntity(entity);
    }

    @Override
    @Transactional
    public ManipulateOrdineMateriePrimeResponse manipulateOrdineMateriePrime(OrdineMateriaPrimaRequest ordineMateriaPrimaRequest) {
        ManipulateOrdineMateriePrimeResponse response = new ManipulateOrdineMateriePrimeResponse();

        Fornitore fornitore = Optional.ofNullable(ordineMateriaPrimaRequest.getIdFornitore())
                .map(idFornitore -> fornitoreRepository.findById(idFornitore)
                        .orElseThrow(() -> new FornitoreNonTrovatoException("Fornitore con id {} non trovato", idFornitore)))
                .orElseThrow(() -> new FornitoreNonTrovatoException("Nessun id fornito per il fornitore!"));

        OrdineDto ordineToReturn = Optional.ofNullable(ordineMateriaPrimaRequest.getIdOrdine())
                .map(idOrdine -> {
                    Ordine ordine = ordineRepository.findById(idOrdine)
                            .orElseThrow(() -> new OrdineNonTrovatoException("Ordine con Id {} non trovato", idOrdine));

                    Set<OrdineMateriaPrima> ordiniMateriaPrima = ordine.getOrdiniMateriaPrima();
                    ordineMateriaPrimaRequest
                            .getIdMateriePrimeQta()
                            .forEach((idMP, qtaMP) -> {
                                Optional<OrdineMateriaPrima> maybeMateriaPrima = ordiniMateriaPrima
                                        .stream()
                                        .filter(ordineMateriaPrima -> ordineMateriaPrima.getMateriaPrima().getId().equals(idMP))
                                        .findFirst();

                                if (maybeMateriaPrima.isPresent()) {
                                    OrdineMateriaPrima ordineMateriaPrima = maybeMateriaPrima.get();
                                    Double quantitaOrdinata = ordineMateriaPrima.getQuantitaOrdinata();
                                    ordineMateriaPrima.setQuantitaOrdinata(quantitaOrdinata + qtaMP);
                                } else {
                                    OrdineMateriaPrima ordineMateriaPrima = new OrdineMateriaPrima();
                                    Optional<MateriaPrima> optionalMateriaPrima = materiaPrimaRepository
                                            .findById(idMP);
                                    if (optionalMateriaPrima.isPresent()) {

                                        ordineMateriaPrima.setMateriaPrima(optionalMateriaPrima.get());
                                        ordineMateriaPrima.setFornitore(fornitore);
                                        ordineMateriaPrima.setOrdine(ordine);
                                        ordineMateriaPrima.setQuantitaOrdinata(qtaMP);
                                        ordiniMateriaPrima.add(ordineMateriaPrima);
                                    } else {
                                        response.getIdMPNotFound().add(idMP);
                                    }

                                }
                            });
                    return ordiniMapper.getOrdineDtoFromEntity(ordineRepository.save(ordine));
                })
                .orElseThrow(() -> new OrdineNonTrovatoException("Nessun id fornito per l'ordine da manipolare!"));
        response.setOrdine(ordineToReturn);
        return response;
    }

    @Override
    public Set<OrdineDto> getAllOrdini() {
        return ordineRepository
                .findAll()
                .stream()
                .map(ordiniMapper::getOrdineDtoFromEntity)
                .collect(Collectors.toSet());
    }

    @Override
    public OrdineDto getOrdineById(Integer id) {
        return ordineRepository
                .findById(id)
                .map(ordiniMapper::getOrdineDtoFromEntity)
                .orElseThrow(() -> new OrdineNonTrovatoException("ordine con id {} non trovato", id));
    }

    @Override
    public void deleteOrdineById(Integer id) {
        ordineRepository.deleteById(id);
    }

    @Scheduled(cron = "${analizza.ordini-anno-precendente.chron}")
    void scheduledAnalizeOrdiniAnnoPrecedente(){
        analizeOrdiniAnnoPrecedente();
    }
    @Transactional
    @Override
//    @Async
    public void analizeOrdiniAnnoPrecedente() {
        Map<String, Set<QuantitaMeseMp>> mapMPQTAUltimoAnnoPerMese = new HashMap<>();
        int lastYear = LocalDate.now().getYear() - 1;

        for (int mese = 1; mese <= 12; mese++) {
            int numeroGiorniMese = YearMonth.of(lastYear, mese).lengthOfMonth();


            LocalDateTime mezzaNotteStart = LocalDateTime.of(LocalDate.of(lastYear, mese, 1), LocalTime.MIN);
            Date startMeseUltimoAnno = Date.from(mezzaNotteStart.toInstant(ZoneOffset.UTC));

            LocalDateTime mezzaNotteEnd = LocalDateTime.of(LocalDate.of(lastYear, mese, numeroGiorniMese), LocalTime.MAX).minus(1, ChronoUnit.HOURS);
            Date endMeseUltimoAnno = Date.from(mezzaNotteEnd.toInstant(ZoneOffset.UTC));

            ordineRepository.getOrdiniPerMeseAnnoPassato(startMeseUltimoAnno, endMeseUltimoAnno)
                    .forEach(ordine -> {
                        ordine.getOrdiniMateriaPrima().forEach(ordineMateriaPrima -> {
                            String idMp = ordineMateriaPrima.getMateriaPrima().getId();
                            Set<QuantitaMeseMp> qtaMeseMp = mapMPQTAUltimoAnnoPerMese.getOrDefault(idMp, new HashSet<>());

                            QuantitaMeseMp quantitaMeseMp = qtaMeseMp
                                    .stream()
                                    .filter(quantitaMese -> quantitaMese.getNumeroMese() == mezzaNotteEnd.getMonth().getValue())
                                    .findAny()
                                    .orElseGet(() -> {
                                        QuantitaMeseMp toReturn = new QuantitaMeseMp();
                                        toReturn.setNumeroMese(mezzaNotteEnd.getMonth().getValue());
                                        return toReturn;
                                    });
                            quantitaMeseMp.addQuantita(ordineMateriaPrima.getQuantitaOrdinata());
                            qtaMeseMp.add(quantitaMeseMp);

                            mapMPQTAUltimoAnnoPerMese.put(idMp, qtaMeseMp);
                        });
                    });
        }
        mapMPQTAUltimoAnnoPerMese.forEach((idMp, previsioniPerMese) -> previsioniPerMese
                .forEach(quantitaMeseMp -> {
                    PrevisioneFabbisognoMp previsioneFabbisognoMp = previsioneFabbisognoMpRepository.findByMeseEqualsAndMateriaPrima_Id(quantitaMeseMp.getNumeroMese(), idMp)
                            .orElseGet(() -> mapper.getEntityFromDto(quantitaMeseMp, idMp));
                    quantitaMeseMp.subtractQuantita(previsioneFabbisognoMp.getQtaNonUsata());
                    previsioneFabbisognoMp.setQuantita(quantitaMeseMp.getQuantita());
                    previsioneFabbisognoMpRepository.save(previsioneFabbisognoMp);
                }));
    }

    private void setQuantitaNonUsata(){
        previsioneFabbisognoMpRepository
                .findAll()
                .forEach(previsioneFabbisognoMp -> previsioneFabbisognoMp.setQtaNonUsata(previsioneFabbisognoMp.getMateriaPrima().getQuantita()));
    }
}