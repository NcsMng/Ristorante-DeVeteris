package com.deveteris.magazzino;

import com.deveteris.magazzino.mapper.PrevisioneFabbisognoMpMapper;
import com.deveteris.magazzino.model.Ordine;
import com.deveteris.magazzino.model.PrevisioneFabbisognoMp;
import com.deveteris.magazzino.repository.OrdineRepository;
import com.deveteris.magazzino.repository.PrevisioneFabbisognoMpRepository;
import com.deveteris.magazzino.requests.OrdineMateriaPrimaRequest;
import com.deveteris.magazzino.response.ManipulateOrdineMateriePrimeResponse;
import com.deveteris.magazzino.services.OrdiniService;
import com.deveteris.magazzino.util.QuantitaMeseMp;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.*;

@SpringBootTest
class MagazzinoApplicationTests {

    @Autowired
    private OrdiniService ordiniService;
    @Autowired
    private OrdineRepository ordineRepository;


    @Test
    void contextLoads() {
    }

    @Test
    void testOrdineMateriePrime() {
        OrdineMateriaPrimaRequest request = new OrdineMateriaPrimaRequest();
        request.setIdOrdine(1);
        Map<String, Double> prodottoOrdinati = new HashMap<>();
        prodottoOrdinati.put("AGO30", 40.2); //Mi aspetto 70.2
        prodottoOrdinati.put("AGO40", 20D);
        prodottoOrdinati.put("AGO50", 10D);
        prodottoOrdinati.put("ABC10", 10.3);
        request.setIdMateriePrimeQta(prodottoOrdinati);
        request.setIdFornitore(1);
        request.setDataOrdinazione(new Date());
        ManipulateOrdineMateriePrimeResponse response = ordiniService.manipulateOrdineMateriePrime(request);
        System.out.println(response);
    }

    @Test
    @Transactional
    void crypt() {
        Map<String, Double> mapMPQTALastYear = new HashMap<>();
        int lastYear = LocalDate.now().getYear() - 1;

        Date dateStartLastYear = Date.from(LocalDateTime.of(lastYear, 1, 1, 0, 0, 0).toInstant(ZoneOffset.UTC));
        Date dateEndLastYear = Date.from(LocalDateTime.of(lastYear, 12, 31, 0, 0, 0).toInstant(ZoneOffset.UTC));

        ordineRepository.getOrdiniPerMeseAnnoPassato(dateStartLastYear, dateEndLastYear)
                .stream().map(Ordine::getOrdiniMateriaPrima).flatMap(Collection::stream)
                .forEach(ordineMateriaPrima -> {
                    String idMp = ordineMateriaPrima.getMateriaPrima().getId();
                    mapMPQTALastYear.put(idMp, mapMPQTALastYear.getOrDefault(idMp, 0.0) + ordineMateriaPrima.getQuantitaOrdinata());
//			Optional<Double> optionalMPQTA = Optional.ofNullable(mapMPQTALastYear.get(ordineMateriaPrima.getMateriaPrima().getId()));
//			if(optionalMPQTA.isPresent()){
//				Double orderedQTALY = optionalMPQTA.get();
//				orderedQTALY+=ordineMateriaPrima.getQuantitaOrdinata();
//				mapMPQTALastYear.put(ordineMateriaPrima.getMateriaPrima().getId(),orderedQTALY);
//			}else {
//				mapMPQTALastYear.put(ordineMateriaPrima.getMateriaPrima().getId(), ordineMateriaPrima.getQuantitaOrdinata());
//
//			}
                });
    }

    @Test
    public void testAnalizeOrdiniAnnoPrecedente(){
        ordiniService.analizeOrdiniAnnoPrecedente();
    }

}
