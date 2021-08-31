package com.deveteris.cucina;

import com.deveteris.cucina.model.Pietanza;
import com.deveteris.cucina.repository.PietanzaRepository;
import com.deveteris.cucina.request.MenuGiornoRequest;
import com.deveteris.cucina.services.CucinaService;
import com.deveteris.cucina.services.MenuGiornoService;
import com.deveteris.notificationsmanager.enums.StatoOrdinazione;
import com.deveteris.notificationsmanager.model.Ordinazione;
import com.deveteris.notificationsmanager.model.PiattoOrdinazione;
import com.deveteris.notificationsmanager.repository.OrdinazioniRepository;
import com.deveteris.notificationsmanager.repository.PiattiOrdinazioneRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class CucinaApplicationTests {
    @Autowired
    private CucinaService cucinaService;
    @Autowired
    private PiattiOrdinazioneRepository piattiOrdinazioneRepository;
    @Autowired
    private OrdinazioniRepository ordinazioniRepository;
    @Autowired
    private PietanzaRepository pietanzaRepository;
    @Autowired
    private MenuGiornoService menuGiornoService;

    @Test
    @Rollback
    @Transactional
    public void testGestioneNotifiche() {
        Pietanza pietanza = new Pietanza();
        pietanza.setNomePiatto("Torta");
        pietanza.setPrezzo(10.0);
        pietanza.setTempoPreparazioneMinuti(1);
        pietanza.setId("ADO10");
        pietanzaRepository.save(pietanza);

        Pietanza pietanza1 = new Pietanza();
        pietanza1.setNomePiatto("Pesce Griglia");
        pietanza1.setPrezzo(13.0);
        pietanza1.setTempoPreparazioneMinuti(0);
        pietanza1.setId("CAP30");
        pietanzaRepository.save(pietanza1);

        Set<MenuGiornoRequest> menuGiorno = new HashSet<>();

        MenuGiornoRequest menuGiornoRequest = new MenuGiornoRequest();
        menuGiornoRequest.setPietanza("CAP30");
        menuGiorno.add(menuGiornoRequest);

        MenuGiornoRequest menuGiornoRequest2 = new MenuGiornoRequest();
        menuGiornoRequest2.setPietanza("ADO10");
        menuGiorno.add(menuGiornoRequest2);

        menuGiornoService.persistMenu(menuGiorno);

        Ordinazione ordinazione = new Ordinazione();
        ordinazione.setNote("Ordinazione Errore");
        ordinazione.setStato(StatoOrdinazione.IN_CARICO);
        Ordinazione ordineEntity = ordinazioniRepository.save(ordinazione);

        PiattoOrdinazione piatto= new PiattoOrdinazione();
        piatto.setCodicePiatto("TTT10");
        piatto.setOrdinazione(ordinazione);
        piatto.setQuantita(2);
        piatto.setNote("Test Piatto");
        ordineEntity.getPiattiOrdinazione().add(piatto);
        piattiOrdinazioneRepository.save(piatto);

        Ordinazione ordinazione1 = new Ordinazione();
        ordinazione1.setNote("Test Preso In Carico");
        Ordinazione ordineEntity1 = ordinazioniRepository.save(ordinazione1);

        PiattoOrdinazione piatto1= new PiattoOrdinazione();
        piatto1.setCodicePiatto("ADO10");
        piatto1.setOrdinazione(ordinazione1);
        piatto1.setQuantita(2);
        piatto1.setNote("Test Piatto");
        ordineEntity1.getPiattiOrdinazione().add(piatto1);
        piattiOrdinazioneRepository.save(piatto1);

        Ordinazione ordinazione2 = new Ordinazione();
        ordinazione2.setNote("Ordinazione PRONTA");
        Ordinazione ordineEntity2 = ordinazioniRepository.save(ordinazione2);

        PiattoOrdinazione piatto2= new PiattoOrdinazione();
        piatto2.setCodicePiatto("CAP30");
        piatto2.setOrdinazione(ordinazione2);
        piatto2.setQuantita(2);
        piatto2.setNote("Test Piatto");
        ordineEntity2.setStato(StatoOrdinazione.IN_CARICO);
        piattiOrdinazioneRepository.save(piatto2);

        try {
            Thread.sleep(60000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        cucinaService.processNotifications();


        Optional<Ordinazione> entityOptional = ordinazioniRepository.findById(ordineEntity.getId());
        assertThat(entityOptional).isPresent();
        assertThat(entityOptional.get()).matches(ordine-> ordine.getStato().equals(StatoOrdinazione.ERRORE));

        Optional<Ordinazione> entity1Optional = ordinazioniRepository.findById(ordineEntity1.getId());
        assertThat(entity1Optional).isPresent();
        assertThat(entity1Optional.get()).matches(ordine-> ordine.getStato().equals(StatoOrdinazione.IN_CARICO));

        Optional<Ordinazione> entity2Optional = ordinazioniRepository.findById(ordineEntity2.getId());
        assertThat(entity2Optional).isPresent();
        assertThat(entity2Optional.get()).matches(ordine-> ordine.getStato().equals(StatoOrdinazione.PRONTO));

    }
}
