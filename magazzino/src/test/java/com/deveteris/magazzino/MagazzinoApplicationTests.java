package com.deveteris.magazzino;

import com.deveteris.magazzino.model.Ordine;
import com.deveteris.magazzino.repository.OrdineRepository;
import com.deveteris.magazzino.requests.MpQtaDto;
import com.deveteris.magazzino.requests.OrdineMateriaPrimaRequest;
import com.deveteris.magazzino.response.ManipulateOrdineMateriePrimeResponse;
import com.deveteris.magazzino.services.OrdiniService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
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
        List<MpQtaDto> list = new ArrayList<>();
        list.add(new MpQtaDto("AGO30", 40.2)); //Mi aspetto 70.2
        list.add(new MpQtaDto("AGO40", 20D));
        list.add(new MpQtaDto("AGO50", 10D));
        list.add(new MpQtaDto("ABC10", 10.3));
        request.setIdMateriePrimeQta(list);
        request.setIdFornitore(1);
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

        ordineRepository.getOrdiniBetween(dateStartLastYear, dateEndLastYear)
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
    @Rollback(value = false)
    public void testAnalizeOrdiniAnnoPrecedente(){
        ordiniService.analizeOrdiniAnnoPrecedente();

    }

}
