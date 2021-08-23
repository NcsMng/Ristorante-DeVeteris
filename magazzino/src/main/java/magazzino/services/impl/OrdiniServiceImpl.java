package magazzino.services.impl;

import magazzino.exceptions.FornitoreNonTrovatoException;
import magazzino.exceptions.MagazzinoException;
import magazzino.exceptions.MateriaPrimaNonTrovataException;
import magazzino.exceptions.OrdineNonTrovatoException;
import magazzino.model.Fornitore;
import magazzino.model.MateriaPrima;
import magazzino.model.Ordine;
import magazzino.model.OrdineMateriaPrima;
import magazzino.repository.FornitoreRepository;
import magazzino.repository.MateriaPrimaRepository;
import magazzino.repository.OrdineRepository;
import magazzino.requests.OrdineMateriaPrimaRequest;
import magazzino.requests.OrdineRequest;
import magazzino.response.ManipulateOrdineMateriePrimeResponse;
import magazzino.services.MateriaPrimaService;
import magazzino.services.OrdiniService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.Set;

@Service
public class OrdiniServiceImpl implements OrdiniService {

    private final OrdineRepository ordineRepository;
    private final FornitoreRepository fornitoreRepository;
    private final MateriaPrimaRepository materiaPrimaRepository;

    public OrdiniServiceImpl(OrdineRepository ordineRepository, FornitoreRepository fornitoreRepository, MateriaPrimaRepository materiaPrimaRepository) {
        this.ordineRepository = ordineRepository;
        this.fornitoreRepository = fornitoreRepository;
        this.materiaPrimaRepository = materiaPrimaRepository;
    }

    @Override
    @Transactional
    public Ordine persistOrdine(OrdineRequest ordine) {
        return Optional.ofNullable(ordine.getId())
                .map(idOrdine -> {
                    Ordine ordineEntity = ordineRepository.findById(idOrdine)
                            .orElseThrow(() -> new OrdineNonTrovatoException("Ordine con Id {} non trovato", ordine.getId()));
                    Optional.ofNullable(ordine.getDataOrdine()).ifPresent(ordineEntity::setDataOrdine);
                    Optional.ofNullable(ordine.getDataConsegna()).ifPresent(ordineEntity::setDataOrdine);
                    return ordineEntity;
                })
                .orElseGet(() -> {
                    Ordine ordineToSave = new Ordine();
                    ordineToSave.setDataOrdine(ordine.getDataOrdine());
                    ordineToSave.setDataConsegna(ordine.getDataConsegna());
                    return ordineRepository.save(ordineToSave);
                });
    }

    @Override
    @Transactional
    public ManipulateOrdineMateriePrimeResponse manipulateOrdineMateriePrime(OrdineMateriaPrimaRequest ordineMateriaPrimaRequest) {
        ManipulateOrdineMateriePrimeResponse response = new ManipulateOrdineMateriePrimeResponse();

        Fornitore fornitore = Optional.ofNullable(ordineMateriaPrimaRequest.getIdFornitore())
                .map(idFornitore -> fornitoreRepository.findById(idFornitore)
                        .orElseThrow(() -> new FornitoreNonTrovatoException("Fornitore con id {} non trovato", idFornitore)))
                .orElseThrow(() -> new MagazzinoException("Nessun id fornito per il fornitore!"));

        Ordine ordineToReturn = Optional.ofNullable(ordineMateriaPrimaRequest.getIdOrdine())
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
                    return ordineRepository.save(ordine);
                })
                .orElseThrow(() -> new MagazzinoException("Nessun id fornito per l'ordine da manipolare!"));
        response.setOrdine(ordineToReturn);
        return response;
    }
}
