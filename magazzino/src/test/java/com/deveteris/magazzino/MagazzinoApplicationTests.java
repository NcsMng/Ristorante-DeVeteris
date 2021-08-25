package com.deveteris.magazzino;

import com.deveteris.magazzino.requests.OrdineMateriaPrimaRequest;
import com.deveteris.magazzino.response.ManipulateOrdineMateriePrimeResponse;
import com.deveteris.magazzino.services.OrdiniService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class MagazzinoApplicationTests {

	@Autowired
	private OrdiniService ordiniService;


	@Test
	void contextLoads() {
	}

	@Test
	void testOrdineMateriePrime(){
		OrdineMateriaPrimaRequest request = new OrdineMateriaPrimaRequest();
		request.setIdOrdine(1);
		Map<String,Double> prodottoOrdinati = new HashMap<>();
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

}
