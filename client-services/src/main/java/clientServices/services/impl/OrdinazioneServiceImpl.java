package clientServices.services.impl;

import clientServices.dto.OrdinazioneRequest;
import clientServices.exception.OrdinazioneNonTrovataException;
import clientServices.mapper.OrdinazioneMapper;
import clientServices.services.OrdinazioneService;
import lombok.extern.slf4j.Slf4j;
import notificationsmanager.model.Ordinazione;
import notificationsmanager.repositories.OrdinazioniRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class OrdinazioneServiceImpl implements OrdinazioneService {

    private final OrdinazioneMapper ordinazioneMapper;
    private final OrdinazioniRepository ordinazioniRepository;

    public OrdinazioneServiceImpl(OrdinazioneMapper ordinazioneMapper, OrdinazioniRepository ordinazioniRepository) {
        this.ordinazioneMapper = ordinazioneMapper;
        this.ordinazioniRepository = ordinazioniRepository;
    }

    //Save or Update Entity via DTO
    //Metodo per Camerieri
    @Override
    public Ordinazione persistOrdinazione(OrdinazioneRequest ordinazioneRequest) {
        return Optional.ofNullable(ordinazioneRequest.getId())
                .map(ordinazioneId ->
                {
                    Ordinazione ordinazione = ordinazioniRepository.findById(ordinazioneId)
                            .orElseThrow(() -> new OrdinazioneNonTrovataException("Ordinazione con id {} non trovata", ordinazioneId));
                    return ordinazioneMapper.updateOrdinazioneFromRequest(ordinazione, ordinazioneRequest);
                })
                .orElseGet(() -> ordinazioniRepository
                        .save(ordinazioneMapper.getEntityFromRequest(ordinazioneRequest)));
    }
    @Override
    //Metodo per Clienti. Si puo' modificare solo se si ha l'uuid dell'ordine, altrimenti si crea nuovo ordine
    public Ordinazione persistOrdinazione(OrdinazioneRequest ordinazioneRequest, String uuidOrdine) {
        if(StringUtils.isNotBlank(uuidOrdine)){
            Ordinazione ordinazione = ordinazioniRepository.findByUuidEquals(uuidOrdine)
                    .orElseThrow(()-> new OrdinazioneNonTrovataException("Ordinazione con uuid {} non trovato",uuidOrdine));
            return ordinazioneMapper.updateOrdinazioneFromRequest(ordinazione,ordinazioneRequest);
        }else {
            return ordinazioniRepository.save(ordinazioneMapper.getEntityFromRequest(ordinazioneRequest));
        }
    }
    //Metodo per Clienti.
    @Override
    public boolean deleteOrdinazione(String uuidOrdine) {
        return ordinazioniRepository.deleteByUuidEquals(uuidOrdine);
    }

    //Metodo Cameriere
    @Override
    public boolean deleteOrdinazione(Integer id) {
        return ordinazioniRepository.deleteByIdEquals(id);
    }
}
