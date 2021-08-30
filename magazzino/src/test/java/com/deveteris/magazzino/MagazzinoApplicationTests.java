package com.deveteris.magazzino;

import com.deveteris.magazzino.enums.StatoOrdine;
import com.deveteris.magazzino.model.Fornitore;
import com.deveteris.magazzino.model.MateriaPrima;
import com.deveteris.magazzino.model.Ordine;
import com.deveteris.magazzino.model.OrdineMateriaPrima;
import com.deveteris.magazzino.repository.*;
import com.deveteris.magazzino.requests.MpQtaDto;
import com.deveteris.magazzino.requests.OrdineMateriaPrimaRequest;
import com.deveteris.magazzino.services.OrdiniService;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;
import java.time.*;
import java.util.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@Transactional

class MagazzinoApplicationTests {
    private static final Logger LOGGER = LoggerFactory.getLogger(MagazzinoApplicationTests.class);

    @Autowired
    private OrdineRepository ordineRepository;
    @Autowired
    private OrdiniService ordiniService;

    @Autowired
    private FornitoreRepository fornitoreRepository;

    @Autowired
    private MateriaPrimaRepository materiaPrimaRepository;

    @Autowired
    private OrdineMateriaPrimaRepository ordineMateriaPrimaRepository;

    @Autowired
    private PrevisioneFabbisognoMpRepository previsioneFabbisognoMpRepository;


    @Test
    @Rollback
    @Transactional
    public void testManipulateOrdini(){
        Fornitore fornitore = new Fornitore();
        fornitore.setNome("Mare e Terra Srl");
        fornitore.setSpecializzazione("prodotti di terra e mare");
        Fornitore fornitoreEntity = fornitoreRepository.save(fornitore);

        Ordine ordine = new Ordine();
        LocalDateTime mezzaNotteStart = LocalDateTime.of(LocalDate.of(2020, 3, 1), LocalTime.MIN);
        Date startMeseUltimoAnno = Date.from(mezzaNotteStart.toInstant(ZoneId.of("Europe/Rome").getRules().getOffset(Instant.now())));
        ordine.setDataOrdine(startMeseUltimoAnno);
        ordine.setDataConsegna(startMeseUltimoAnno);
        ordine.setStatoOrdine(StatoOrdine.CONSEGNATO);
        Ordine ordineEntity = ordineRepository.save(ordine);

        MateriaPrima materiaPrima = new MateriaPrima();
        materiaPrima.setDescrizione("Rossa");
        materiaPrima.setNome("Carne");
        materiaPrima.setQuantita(30.0);
        materiaPrima.setId("BSP10");
        MateriaPrima materiaPrimaEntity = materiaPrimaRepository.save(materiaPrima);

        MateriaPrima materiaPrima1 = new MateriaPrima();
        materiaPrima1.setDescrizione("Bianca");
        materiaPrima1.setNome("Carne");
        materiaPrima1.setQuantita(35.0);
        materiaPrima1.setId("BSP20");
        MateriaPrima materiaPrimaEntity1 = materiaPrimaRepository.save(materiaPrima1);

        OrdineMateriaPrimaRequest ordineMateriaPrimaRequest = new OrdineMateriaPrimaRequest();
        ordineMateriaPrimaRequest.setIdOrdine(ordineEntity.getId());
        ordineMateriaPrimaRequest.setIdFornitore(fornitoreEntity.getId());
        MpQtaDto mpQtaDto = new MpQtaDto(materiaPrimaEntity.getId(), 40.40);
        MpQtaDto mpQtaDto1 = new MpQtaDto(materiaPrimaEntity1.getId(), 100.40);
        ArrayList<MpQtaDto> mpQtaDtos = new ArrayList<>();
        mpQtaDtos.add(mpQtaDto);
        mpQtaDtos.add(mpQtaDto1);
        ordineMateriaPrimaRequest.setIdMateriePrimeQta(mpQtaDtos);

        ordiniService.manipulateOrdineMateriePrime(ordineMateriaPrimaRequest);

        Optional<OrdineMateriaPrima> ordineMp = ordineMateriaPrimaRepository.findByFornitore_IdAndOrdine_IdAndMateriaPrima_Id(fornitoreEntity.getId(), ordineEntity.getId(), mpQtaDto.getIdMp());
        assertThat(ordineMp).isPresent();
        assertThat(ordineMp.get()).matches(ordineMateriaPrima -> ordineMateriaPrima.getQuantitaOrdinata()==40.4);

        Optional<OrdineMateriaPrima> ordineMp1 = ordineMateriaPrimaRepository.findByFornitore_IdAndOrdine_IdAndMateriaPrima_Id(fornitoreEntity.getId(), ordineEntity.getId(), mpQtaDto1.getIdMp());
        assertThat(ordineMp1).isPresent();
        assertThat(ordineMp1.get()).matches(ordineMateriaPrima -> ordineMateriaPrima.getQuantitaOrdinata()==100.4);

        ordiniService.manipulateOrdineMateriePrime(ordineMateriaPrimaRequest);

        Optional<OrdineMateriaPrima> ordineMp2 = ordineMateriaPrimaRepository.findByFornitore_IdAndOrdine_IdAndMateriaPrima_Id(fornitoreEntity.getId(), ordineEntity.getId(), mpQtaDto.getIdMp());
        assertThat(ordineMp2).isPresent();
        assertThat(ordineMp2.get()).matches(ordineMateriaPrima -> ordineMateriaPrima.getQuantitaOrdinata()==80.8);

        Optional<OrdineMateriaPrima> ordineMp3 = ordineMateriaPrimaRepository.findByFornitore_IdAndOrdine_IdAndMateriaPrima_Id(fornitoreEntity.getId(), ordineEntity.getId(), mpQtaDto1.getIdMp());
        assertThat(ordineMp3).isPresent();
        assertThat(ordineMp3.get()).matches(ordineMateriaPrima -> ordineMateriaPrima.getQuantitaOrdinata()==200.8);
    }
    @Test
    @Rollback
    @Transactional
    public void testAnalizeOrdiniAnnoPrecedente() {
        previsioneFabbisognoMpRepository.deleteAll();
        Fornitore fornitore = new Fornitore();
        fornitore.setNome("Mare e Terra Srl");
        fornitore.setSpecializzazione("prodotti di terra e mare");
        Fornitore fornitoreEntity = fornitoreRepository.save(fornitore);

        MateriaPrima materiaPrima = new MateriaPrima();
        materiaPrima.setDescrizione("Rossa");
        materiaPrima.setNome("Carne");
        materiaPrima.setQuantita(30.0);
        materiaPrima.setId("BSP10");
        MateriaPrima materiaPrimaEntity = materiaPrimaRepository.save(materiaPrima);

        MateriaPrima materiaPrima1 = new MateriaPrima();
        materiaPrima1.setDescrizione("Bianca");
        materiaPrima1.setNome("Carne");
        materiaPrima1.setQuantita(50.0);
        materiaPrima1.setId("BAP30");
        MateriaPrima materiaPrima1Entity = materiaPrimaRepository.save(materiaPrima1);

        MateriaPrima materiaPrima2 = new MateriaPrima();
        materiaPrima2.setDescrizione("pochi grassi");
        materiaPrima2.setNome("Pesce");
        materiaPrima2.setQuantita(14.4);
        materiaPrima2.setId("BAO40");
        MateriaPrima materiaPrima2Entity = materiaPrimaRepository.save(materiaPrima2);

        Ordine ordine = new Ordine();
        LocalDateTime mezzaNotteStart = LocalDateTime.of(LocalDate.of(2020, 3, 1), LocalTime.MIN);
        Date startMeseUltimoAnno = Date.from(mezzaNotteStart.toInstant(ZoneId.of("Europe/Rome").getRules().getOffset(Instant.now())));
        ordine.setDataOrdine(startMeseUltimoAnno);
        ordine.setDataConsegna(startMeseUltimoAnno);
        ordine.setStatoOrdine(StatoOrdine.CONSEGNATO);
        Ordine ordineEntity = ordineRepository.save(ordine);

        Ordine ordine1 = new Ordine();
        LocalDateTime mezzaNotteStart1 = LocalDateTime.of(LocalDate.of(2020, 4, 1), LocalTime.MIN);
        Date startMeseUltimoAnno1 = Date.from(mezzaNotteStart1.toInstant(ZoneId.of("Europe/Rome").getRules().getOffset(Instant.now())));
        ordine1.setDataOrdine(startMeseUltimoAnno1);
        ordine1.setDataConsegna(startMeseUltimoAnno1);
        ordine1.setStatoOrdine(StatoOrdine.CONSEGNATO);
        Ordine ordine1Entity = ordineRepository.save(ordine1);


        OrdineMateriaPrima ordineMateriaPrima4 = new OrdineMateriaPrima();
        ordineMateriaPrima4.setQuantitaOrdinata(100.0);
        ordineMateriaPrima4.setFornitore(fornitore);
        ordineMateriaPrima4.setOrdine(ordine);
        ordineMateriaPrima4.setMateriaPrima(materiaPrima);
        OrdineMateriaPrima ordineMateriaPrima4Entity = ordineMateriaPrimaRepository.save(ordineMateriaPrima4);

        OrdineMateriaPrima ordineMateriaPrima3 = new OrdineMateriaPrima();
        ordineMateriaPrima3.setQuantitaOrdinata(120.0);
        ordineMateriaPrima3.setFornitore(fornitore);
        ordineMateriaPrima3.setOrdine(ordine);
        ordineMateriaPrima3.setMateriaPrima(materiaPrima1);
        OrdineMateriaPrima ordineMateriaPrima3Entity = ordineMateriaPrimaRepository.save(ordineMateriaPrima3);


        OrdineMateriaPrima ordineMateriaPrima2 = new OrdineMateriaPrima();
        ordineMateriaPrima2.setQuantitaOrdinata(50.0);
        ordineMateriaPrima2.setFornitore(fornitore);
        ordineMateriaPrima2.setOrdine(ordine);
        ordineMateriaPrima2.setMateriaPrima(materiaPrima2);
        OrdineMateriaPrima ordineMateriaPrima2Entity = ordineMateriaPrimaRepository.save(ordineMateriaPrima2);


        OrdineMateriaPrima ordineMateriaPrima1 = new OrdineMateriaPrima();
        ordineMateriaPrima1.setQuantitaOrdinata(100.0);
        ordineMateriaPrima1.setFornitore(fornitore);
        ordineMateriaPrima1.setOrdine(ordine1);
        ordineMateriaPrima1.setMateriaPrima(materiaPrima);
        OrdineMateriaPrima ordineMateriaPrima1Entity = ordineMateriaPrimaRepository.save(ordineMateriaPrima1);


        OrdineMateriaPrima ordineMateriaPrima = new OrdineMateriaPrima();
        ordineMateriaPrima.setQuantitaOrdinata(100.0);
        ordineMateriaPrima.setFornitore(fornitore);
        ordineMateriaPrima.setOrdine(ordine1);
        ordineMateriaPrima.setMateriaPrima(materiaPrima1);
        OrdineMateriaPrima ordineMateriaPrimaEntity = ordineMateriaPrimaRepository.save(ordineMateriaPrima);


        OrdineMateriaPrima ordineMateriaPrima5 = new OrdineMateriaPrima();
        ordineMateriaPrima5.setQuantitaOrdinata(100.0);
        ordineMateriaPrima5.setFornitore(fornitore);
        ordineMateriaPrima5.setOrdine(ordine1);
        ordineMateriaPrima5.setMateriaPrima(materiaPrima2);
        OrdineMateriaPrima ordineMateriaPrima5Entity = ordineMateriaPrimaRepository.save(ordineMateriaPrima5);

        Set<OrdineMateriaPrima> ordineMateriaForniore = new HashSet<>();
        ordineMateriaForniore.add(ordineMateriaPrima);
        ordineMateriaForniore.add(ordineMateriaPrima1);
        ordineMateriaForniore.add(ordineMateriaPrima2);
        ordineMateriaForniore.add(ordineMateriaPrima3);
        ordineMateriaForniore.add(ordineMateriaPrima4);
        ordineMateriaForniore.add(ordineMateriaPrima5);
        fornitoreEntity.getOrdiniMateriaPrima().addAll(ordineMateriaForniore);

        Set<OrdineMateriaPrima> ordineMateriaOrdinazione = new HashSet<>();
        ordineMateriaOrdinazione.add(ordineMateriaPrima4);
        ordineMateriaOrdinazione.add(ordineMateriaPrima3);
        ordineMateriaOrdinazione.add(ordineMateriaPrima2);
        ordineEntity.getOrdiniMateriaPrima().addAll(ordineMateriaOrdinazione);

        Set<OrdineMateriaPrima> ordineMateriaOrdinazione1 = new HashSet<>();
        ordineMateriaOrdinazione1.add(ordineMateriaPrima);
        ordineMateriaOrdinazione1.add(ordineMateriaPrima1);
        ordineMateriaOrdinazione1.add(ordineMateriaPrima5);
        ordine1Entity.getOrdiniMateriaPrima().addAll(ordineMateriaOrdinazione1);

        Set<OrdineMateriaPrima> ordineMateriaMateriaPrima = new HashSet<>();
        ordineMateriaMateriaPrima.add(ordineMateriaPrima4);
        ordineMateriaMateriaPrima.add(ordineMateriaPrima1);
        materiaPrimaEntity.getOrdiniMateriaPrima().addAll(ordineMateriaMateriaPrima);

        Set<OrdineMateriaPrima> ordineMateriaMateriaPrima1 = new HashSet<>();
        ordineMateriaMateriaPrima1.add(ordineMateriaPrima);
        ordineMateriaMateriaPrima1.add(ordineMateriaPrima3);
        materiaPrima1Entity.getOrdiniMateriaPrima().addAll(ordineMateriaMateriaPrima1);

        Set<OrdineMateriaPrima> ordineMateriaMateriaPrima2 = new HashSet<>();
        ordineMateriaMateriaPrima2.add(ordineMateriaPrima5);
        ordineMateriaMateriaPrima2.add(ordineMateriaPrima3);
        ordineMateriaMateriaPrima2.add(ordineMateriaPrima2);
        materiaPrima2Entity.getOrdiniMateriaPrima().addAll(ordineMateriaMateriaPrima2);


        ordiniService.analizeOrdiniAnnoPrecedente();
        previsioneFabbisognoMpRepository.findAll().forEach(previsioneFabbisognoMp -> {
            LOGGER.debug("\n CodiceMateriaPrima: {} \n Quantita: {} \n Mese: {} \n QuantitaNonUsata: {}",
                    previsioneFabbisognoMp.getMateriaPrima().getId(),
                    previsioneFabbisognoMp.getQuantita(),
                    previsioneFabbisognoMp.getMese(),
                    previsioneFabbisognoMp.getQtaNonUsata());
        });
        assertThat(previsioneFabbisognoMpRepository.findByMeseEqualsAndMateriaPrima_Id(3, "BAP30"))
                .isNotEmpty();

    }

}
