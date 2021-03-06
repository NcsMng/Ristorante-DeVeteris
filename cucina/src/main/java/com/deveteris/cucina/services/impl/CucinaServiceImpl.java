package com.deveteris.cucina.services.impl;

import com.deveteris.cucina.model.PiattoMenuGiorno;
import com.deveteris.cucina.model.Pietanza;
import com.deveteris.cucina.repository.MenuGiornoRepository;
import com.deveteris.cucina.repository.PietanzaRepository;
import com.deveteris.cucina.services.CucinaService;
import com.deveteris.notificationsmanager.enums.StatoOrdinazione;
import com.deveteris.notificationsmanager.model.Ordinazione;
import com.deveteris.notificationsmanager.model.PiattoOrdinazione;
import com.deveteris.notificationsmanager.repository.OrdinazioniRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
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
    private static final Logger LOGGER = LoggerFactory.getLogger(CucinaServiceImpl.class);

    private final OrdinazioniRepository ordinazioniRepository;
    private final PietanzaRepository pietanzaRepository;
    private final MenuGiornoRepository menuGiornoRepository;

    public CucinaServiceImpl(OrdinazioniRepository ordinazioniRepository, PietanzaRepository pietanzaRepository, MenuGiornoRepository menuGiornoRepository) {
        this.ordinazioniRepository = ordinazioniRepository;
        this.pietanzaRepository = pietanzaRepository;
        this.menuGiornoRepository = menuGiornoRepository;
    }

    @Override
    @Scheduled(cron = "${process-notifications.cron}")
    @Async
    @Transactional
    public void processNotifications() {
        LOGGER.debug("Searching for active notifications...");
        List<Ordinazione> allByStatoEquals = ordinazioniRepository.findAllByStatoNot(StatoOrdinazione.PRONTO);
        if (!CollectionUtils.isEmpty(allByStatoEquals)) {
            LOGGER.debug("Processing {} notifications...", allByStatoEquals.size());
            allByStatoEquals.forEach(ordinazione -> {
                if (ordinazione.getStato().equals(StatoOrdinazione.IN_ATTESA)) {
                    processNotificationInAttesa(ordinazione.getId());
                } else if (ordinazione.getStato().equals(StatoOrdinazione.IN_CARICO)) {
                    processNotificationinCarico(ordinazione.getId());
                }
            });
        }
    }

    public void processNotificationInAttesa(Integer id) {
        ordinazioniRepository.findById(id)
                .ifPresent(ordinazione -> {
                    ordinazione.setStato(StatoOrdinazione.IN_CARICO);
                    ordinazioniRepository.save(ordinazione);
                    LOGGER.debug("Ordinazione {} presa in carico", ordinazione.getId());
                });
    }

    public void processNotificationinCarico(Integer id) {
        ordinazioniRepository.findById(id)
                .ifPresent(ordinazione -> {

                    Date date = new Date();
                    long timeDifference = Math.abs(date.getTime() - ordinazione.getUpdatedDate().getTime());
                    long diff = TimeUnit.MINUTES.convert(timeDifference, TimeUnit.MILLISECONDS);

                    Optional<String> anyPiattoNonTerminato = ordinazione.getPiattiOrdinazione()
                            .stream()
                            .filter(piattoOrdinazione -> piattoOrdinazione.getQuantita() > 0)
                            .map(PiattoOrdinazione::getCodicePiatto)
                            .filter(codicePiatto -> {
                                Optional<PiattoMenuGiorno> presenzaMenuGiorno = menuGiornoRepository.findByPietanza_Id(codicePiatto);

                                if (presenzaMenuGiorno.isPresent()) {
                                    Optional<Pietanza> pietanza = pietanzaRepository.findById(codicePiatto);
                                    if (pietanza.isPresent()) {
                                        return diff <= pietanza.get().getTempoPreparazioneMinuti();
                                    } else {
                                        ordinazione.setStato(StatoOrdinazione.ERRORE);
                                        return true;
                                    }
                                } else {
                                    ordinazione.setStato(StatoOrdinazione.ERRORE);
                                    return true;
                                }
                            })
                            .findAny();

                    if (!anyPiattoNonTerminato.isPresent()) {
                        LOGGER.debug("Ordine {} e' pronto per essere ritirato", ordinazione.getId());
                        ordinazione.setStato(StatoOrdinazione.PRONTO);
                    }

                    if (ordinazione.getStato().equals(StatoOrdinazione.ERRORE)) {
                        ordinazione.setCosto(0.0);
                    } else if (!Optional.ofNullable(ordinazione.getCosto()).isPresent()) {
                        double costoOrdinazione = ordinazione.getPiattiOrdinazione()
                                .stream()
                                .mapToDouble(piatto -> {
                                    Optional<Pietanza> entityOptional = pietanzaRepository.findById(piatto.getCodicePiatto());
                                    return entityOptional.map(pietanza -> pietanza.getPrezzo() * piatto.getQuantita())
                                            .orElse(0.0);
                                }).sum();
                        ordinazione.setCosto(costoOrdinazione);
                    }
                    ordinazioniRepository.save(ordinazione);
                });
    }
}

