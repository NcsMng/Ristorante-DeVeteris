package ristorante.de.veteris.services.impl;

import ristorante.de.veteris.exception.PietanzaNonTrovataException;
import ristorante.de.veteris.model.Pietanza;
import ristorante.de.veteris.repository.PietanzaRepository;
import ristorante.de.veteris.services.CucinaService;
import ristorante.de.veteris.enums.StatoOrdinazione;
import ristorante.de.veteris.model.Ordinazione;
import ristorante.de.veteris.model.PiattoOrdinazione;
import ristorante.de.veteris.repository.OrdinazioniRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
public class CucinaServiceImpl implements CucinaService {

    private final OrdinazioniRepository ordinazioniRepository;
    private final PietanzaRepository pietanzaRepository;

    public CucinaServiceImpl(OrdinazioniRepository ordinazioniRepository, PietanzaRepository pietanzaRepository) {
        this.ordinazioniRepository = ordinazioniRepository;
        this.pietanzaRepository = pietanzaRepository;
    }

    @Override
    @Scheduled(cron = "")
    public void processNotifications() {
        List<Ordinazione> allByStatoEquals = ordinazioniRepository.findAllByStatoNot(StatoOrdinazione.PRONTO);
        if (!CollectionUtils.isEmpty(allByStatoEquals)) {
            allByStatoEquals.forEach(ordinazione -> {
                if (ordinazione.getStato().equals(StatoOrdinazione.IN_ATTESA)) {
                    processNotificationInAttesa(ordinazione.getId());
                } else {
                    processNotificationinCarico(ordinazione.getId());
                }
            });
        }
    }
    @Transactional
    public void processNotificationInAttesa(Integer id) {
        ordinazioniRepository.findById(id)
                .ifPresent(ordinazione -> {
                    ordinazione.setStato(StatoOrdinazione.IN_CARICO);
                });
    }
    @Transactional
    public void processNotificationinCarico(Integer id) {
        ordinazioniRepository.findById(id)
                .ifPresent(ordinazione -> {
                    Date date = new Date();
                    long timeDifference = Math.abs(date.getTime() - ordinazione.getUpdatedDate().getTime());
                    long diff = TimeUnit.MINUTES.convert(timeDifference, TimeUnit.MILLISECONDS);
                    Optional<String> anyPiattoNonTerminato = ordinazione.getPiattiOrdinazione()
                            .stream()
                            .map(PiattoOrdinazione::getCodicePiatto)
                            .filter(codicePiatto -> {
                                Pietanza pietanza = pietanzaRepository.findById(codicePiatto)
                                        .orElseThrow(() -> new PietanzaNonTrovataException("Pietanza con id {} non trovata", codicePiatto));
                                return diff < pietanza.getTempoPreparazioneMinuti();
                            })
                            .findAny();
                    if(!anyPiattoNonTerminato.isPresent()){
                        ordinazione.setStato(StatoOrdinazione.PRONTO);
                    }
                });
    }
}
