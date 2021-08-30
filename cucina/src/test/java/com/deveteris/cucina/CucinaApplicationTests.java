package com.deveteris.cucina;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class CucinaApplicationTests {

//	@Mock
//	private PiattiOrdinazioneRepository piattiOrdinazioneRepository;
//	@Mock
//	private OrdinazioniRepository ordinazioniRepository;
//	@Mock
//	private PietanzaRepository pietanzaRepository;
//	@Mock
//	private MenuGiornoRepository menuGiornoRepository;
//
//	private CucinaService underTest;
//
//	@BeforeEach
//	void setUp(){
//		underTest = new CucinaServiceImpl(ordinazioniRepository, pietanzaRepository,menuGiornoRepository);
//	}
//	@Test
//	void shouldSetStatusToPronto(){
//		Ordinazione ordinazione = new Ordinazione();
//		ordinazione.setStato(StatoOrdinazione.IN_CARICO);
//		ordinazione.setNote("poco sale");
//		ordinazione.setId(1000);
//		Set<PiattoOrdinazione> piattiOrdinazione = new HashSet<>();
//
//		PiattoOrdinazione piattoStrudel = new PiattoOrdinazione();
//		piattoStrudel.setQuantita(2);
//		piattoStrudel.setNote("piu' zucchero");
//		piattoStrudel.setCodicePiatto("APL15");
//		piattoStrudel.setOrdinazione(ordinazione);
//		piattiOrdinazione.add(piattoStrudel);
//
//		PiattoOrdinazione piattoPatate = new PiattoOrdinazione();
//		piattoPatate.setQuantita(5);
//		piattoPatate.setNote("meno sale");
//		piattoPatate.setCodicePiatto("PAT20");
//		piattoPatate.setOrdinazione(ordinazione);
//		piattiOrdinazione.add(piattoPatate);
//
//		ordinazione.setPiattiOrdinazione(piattiOrdinazione);
//
//		ordinazioniRepository.save(ordinazione);
//		ordinazione.setStato(StatoOrdinazione.PRONTO);
//		ordinazione.setCosto(24.5);
//		underTest.processNotifications();
//		ArgumentCaptor<Ordinazione> ordinazioneArgumentCaptor = ArgumentCaptor.forClass(Ordinazione.class);
//		verify(ordinazioniRepository).save(ordinazioneArgumentCaptor.capture());
//
//		Ordinazione ordinazioneCatturata = ordinazioneArgumentCaptor.getValue();
//		assertThat(ordinazioneCatturata).isEqualTo(ordinazione);
//	}


}
