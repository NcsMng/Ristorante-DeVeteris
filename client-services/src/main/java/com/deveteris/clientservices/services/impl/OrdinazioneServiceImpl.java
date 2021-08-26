package com.deveteris.clientservices.services.impl;

import com.deveteris.clientservices.exception.OrdinazioneNonTrovataException;
import com.deveteris.clientservices.request.OrdinazioneRequest;
import com.deveteris.clientservices.dto.OrdinazioneDto;
import com.deveteris.clientservices.mapper.OrdinazioneMapper;
import com.deveteris.clientservices.services.OrdinazioneService;
import com.deveteris.notificationsmanager.model.Ordinazione;
import com.deveteris.notificationsmanager.repository.OrdinazioniRepository;
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
    public OrdinazioneDto persistOrdinazione(OrdinazioneRequest ordinazioneRequest) {
        Ordinazione entity = Optional.ofNullable(ordinazioneRequest.getId())
                .map(ordinazioneId ->
                {
                    Ordinazione ordinazione = ordinazioniRepository.findById(ordinazioneId)
                            .orElseThrow(() -> new OrdinazioneNonTrovataException("Ordinazione con id {} non trovata", ordinazioneId));
                    return ordinazioneMapper.updateOrdinazioneFromRequest(ordinazione, ordinazioneRequest);
                })
                .orElseGet(() -> ordinazioneMapper.getEntityFromRequest(ordinazioneRequest));
        return ordinazioneMapper.getDtoFromEntity(ordinazioniRepository.save(entity));
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
    //Metodo per Clienti. Si puo' modificare solo se si ha l'uuid dell'ordine, altrimenti si crea nuovo ordine
    public OrdinazioneDto persistOrdinazione(OrdinazioneRequest ordinazioneRequest, String uuidOrdine) {
        Ordinazione savedEntity;
        if (StringUtils.isNotBlank(uuidOrdine)) {
            Ordinazione ordinazione = ordinazioniRepository.findByUuidEquals(uuidOrdine)
                    .orElseThrow(() -> new OrdinazioneNonTrovataException("Ordinazione con uuid {} non trovato", uuidOrdine));
            savedEntity = ordinazioneMapper.updateOrdinazioneFromRequest(ordinazione, ordinazioneRequest);
        } else {
            savedEntity = ordinazioneMapper.getEntityFromRequest(ordinazioneRequest);
        }
        return ordinazioneMapper.getDtoFromEntity(ordinazioniRepository.save(savedEntity));
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
    public Integer deleteOrdinazione(String uuidOrdine) {
        return ordinazioniRepository.deleteByUuidEquals(uuidOrdine);
    }

    //Metodo Cameriere
    @Override
    public boolean deleteOrdinazione(Integer id) {
        return ordinazioniRepository.deleteByIdEquals(id);
    }
}
