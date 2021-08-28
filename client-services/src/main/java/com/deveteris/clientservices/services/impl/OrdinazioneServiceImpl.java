package com.deveteris.clientservices.services.impl;

import com.deveteris.clientservices.dto.OrdinazioneDto;
import com.deveteris.clientservices.mapper.OrdinazioneMapper;
import com.deveteris.clientservices.mapper.PiattoOrdinazioneMapper;
import com.deveteris.clientservices.request.OrdinazioneRequest;
import com.deveteris.clientservices.services.OrdinazioneService;
import com.deveteris.notificationsmanager.exceptions.OrdinazioneNonTrovataException;
import com.deveteris.notificationsmanager.model.Ordinazione;
import com.deveteris.notificationsmanager.repository.OrdinazioniRepository;
import com.deveteris.notificationsmanager.repository.PiattiOrdinazioneRepository;
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
    private final PiattiOrdinazioneRepository piattiOrdinazioneRepository;
    private final PiattoOrdinazioneMapper piattoOrdinazioneMapper;
    public OrdinazioneServiceImpl(OrdinazioneMapper ordinazioneMapper, OrdinazioniRepository ordinazioniRepository, PiattiOrdinazioneRepository piattiOrdinazioneRepository, PiattoOrdinazioneMapper piattoOrdinazioneMapper) {
        this.ordinazioneMapper = ordinazioneMapper;
        this.ordinazioniRepository = ordinazioniRepository;
        this.piattiOrdinazioneRepository = piattiOrdinazioneRepository;
        this.piattoOrdinazioneMapper = piattoOrdinazioneMapper;
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
                    return ordinazioneMapper.updateOrdinazioneFromRequest(ordinazione, ordinazioneRequest, piattiOrdinazioneRepository, piattoOrdinazioneMapper);
                })
                .orElseGet(() -> ordinazioneMapper.getEntityFromRequest(ordinazioneRequest,piattiOrdinazioneRepository,piattoOrdinazioneMapper));
        return ordinazioneMapper.getDtoFromEntity(ordinazioniRepository.save(entity), piattoOrdinazioneMapper);
    }

    @Override
    public OrdinazioneDto getOrdine(Integer id) {
        return ordinazioneMapper
                .getDtoFromEntity(ordinazioniRepository
                        .findByIdEquals(id)
                        .orElseThrow(() -> new OrdinazioneNonTrovataException("ordinazione {} non trovata", id)),piattoOrdinazioneMapper);
    }

    @Override
    public List<OrdinazioneDto> getAllOrdini() {
        return ordinazioniRepository
                .findAll()
                .stream()
                .map(ordinazione -> ordinazioneMapper.getDtoFromEntity(ordinazione,piattoOrdinazioneMapper)).collect(Collectors.toList());
    }

    @Override
    //Metodo per Clienti. Si puo' modificare solo se si ha l'uuid dell'ordine, altrimenti si crea nuovo ordine
    public OrdinazioneDto persistOrdinazione(OrdinazioneRequest ordinazioneRequest, String uuidOrdine) {
        Ordinazione savedEntity;
        if (StringUtils.isNotBlank(uuidOrdine)) {
            Ordinazione ordinazione = ordinazioniRepository.findByUuidEquals(uuidOrdine)
                    .orElseThrow(() -> new OrdinazioneNonTrovataException("Ordinazione con uuid {} non trovato", uuidOrdine));
            savedEntity = ordinazioneMapper.updateOrdinazioneFromRequest(ordinazione, ordinazioneRequest, piattiOrdinazioneRepository,piattoOrdinazioneMapper);
        } else {
            savedEntity = ordinazioneMapper.getEntityFromRequest(ordinazioneRequest, piattiOrdinazioneRepository, piattoOrdinazioneMapper);
        }
        return ordinazioneMapper.getDtoFromEntity(ordinazioniRepository.save(savedEntity), piattoOrdinazioneMapper);
    }

    @Override
    public OrdinazioneDto getOrdine(String uuid) {
        return ordinazioneMapper
                .getDtoFromEntity(ordinazioniRepository
                        .findByUuidEquals(uuid)
                        .orElseThrow(() -> new OrdinazioneNonTrovataException("Ordine con identificativo passato non esiste")), piattoOrdinazioneMapper);
    }

    //Metodo per Clienti.
    @Override
    @Transactional
    public boolean deleteOrdinazione(String uuidOrdine) {
        return ordinazioniRepository.deleteByUuidEquals(uuidOrdine) == 1;
    }

    //Metodo Cameriere
    @Override
    @Transactional
    public boolean deleteOrdinazione(Integer id) {
        return ordinazioniRepository.deleteByIdEquals(id)==1;
    }
}
