package ristorante.de.veteris.services.impl;

import ristorante.de.veteris.dto.OrdinazioneDto;
import ristorante.de.veteris.exception.OrdinazioneNonTrovataException;
import ristorante.de.veteris.mapper.OrdinazioneMapper;
import ristorante.de.veteris.request.OrdinazioneRequest;
import ristorante.de.veteris.services.OrdinazioneService;
import ristorante.de.veteris.model.Ordinazione;
import ristorante.de.veteris.repository.OrdinazioniRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
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
    @Transactional
    public OrdinazioneDto persistOrdinazione(OrdinazioneRequest ordinazioneRequest) {
        Ordinazione entity = Optional.ofNullable(ordinazioneRequest.getId())
                .map(ordinazioneId ->
                {
                    Ordinazione ordinazione = ordinazioniRepository.findById(ordinazioneId)
                            .orElseThrow(() -> new OrdinazioneNonTrovataException("Ordinazione con id {} non trovata", ordinazioneId));
                    return ordinazioneMapper.updateOrdinazioneFromRequest(ordinazione, ordinazioneRequest);
                })
                .orElseGet(() -> ordinazioniRepository
                        .save(ordinazioneMapper.getEntityFromRequest(ordinazioneRequest)));
        return ordinazioneMapper.getDtoFromEntity(entity);
    }

    @Override
    public OrdinazioneDto getOrdine(Integer id) {
        return ordinazioneMapper
                .getDtoFromEntity(ordinazioniRepository
                        .findByIdEquals(id)
                        .orElseThrow(() -> new OrdinazioneNonTrovataException("ordinazione {} non trovata", id)));
    }

    @Override
    public List<OrdinazioneDto> getAllOrdini() {
        return ordinazioniRepository
                .findAll()
                .stream()
                .map(ordinazioneMapper::getDtoFromEntity).collect(Collectors.toList());
    }

    @Override
    @Transactional
    //Metodo per Clienti. Si puo' modificare solo se si ha l'uuid dell'ordine, altrimenti si crea nuovo ordine
    public OrdinazioneDto persistOrdinazione(OrdinazioneRequest ordinazioneRequest, String uuidOrdine) {
        Ordinazione savedEntity;
        if (StringUtils.isNotBlank(uuidOrdine)) {
            Ordinazione ordinazione = ordinazioniRepository.findByUuidEquals(uuidOrdine)
                    .orElseThrow(() -> new OrdinazioneNonTrovataException("Ordinazione con uuid {} non trovato", uuidOrdine));
            savedEntity = ordinazioneMapper.updateOrdinazioneFromRequest(ordinazione, ordinazioneRequest);
        } else {
            savedEntity = ordinazioniRepository.save(ordinazioneMapper.getEntityFromRequest(ordinazioneRequest));
        }
        return ordinazioneMapper.getDtoFromEntity(savedEntity);
    }

    @Override
    public OrdinazioneDto getOrdine(String uuid) {
        return ordinazioneMapper
                .getDtoFromEntity(ordinazioniRepository
                        .findByUuidEquals(uuid)
                        .orElseThrow(() -> new OrdinazioneNonTrovataException("Ordine con identificativo passato non esiste")));
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
