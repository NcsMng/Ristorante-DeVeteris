package com.deveteris.cucina.services.impl;

import com.deveteris.cucina.dto.PietanzaDto;
import com.deveteris.cucina.exception.PietanzaNonTrovataException;
import com.deveteris.cucina.model.Pietanza;
import com.deveteris.cucina.repository.PietanzaRepository;
import com.deveteris.cucina.services.PietanzeService;
import com.deveteris.cucina.request.PietanzaRequest;
import com.deveteris.cucina.mapper.PietanzaMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PietanzeServiceImpl implements PietanzeService {

    private final PietanzaRepository pietanzaRepository;
    private final PietanzaMapper pietanzaMapper;

    public PietanzeServiceImpl(PietanzaRepository pietanzaRepository, PietanzaMapper pietanzaMapper) {
        this.pietanzaRepository = pietanzaRepository;
        this.pietanzaMapper = pietanzaMapper;
    }

    //Metodo solo per chef e manager
    @Override
    public PietanzaDto persistPietanza(PietanzaRequest pietanzaRequest) {
        Pietanza pietanza = Optional.ofNullable(pietanzaRequest.getId())
                .map(pietanzaId ->
                {
                    Pietanza pietanze = pietanzaRepository.findById(pietanzaId)
                            .orElseGet(() -> pietanzaMapper.getEntityFromRequest(pietanzaRequest));
                    return pietanzaMapper.updatePietanzaFromRequest(pietanze, pietanzaRequest);
                })
                .orElseThrow(() -> new PietanzaNonTrovataException("id necessario per inserimento o modifica pietanza!"));
        return pietanzaMapper.getPietanzaDtoFromEntity(pietanzaRepository.save(pietanza));
    }

    //Metodo solo per chef e manager
    @Override
    @Transactional
    public void deletePietanza(String id) {
        pietanzaRepository.deleteById(id);
    }

    @Override
    public Set<PietanzaDto> getAllPietanze() {
        return pietanzaRepository
                .findAll()
                .stream()
                .map(pietanzaMapper::getPietanzaDtoFromEntity)
                .collect(Collectors.toSet());
    }

    @Override
    public PietanzaDto getPietanzaById(String idPietanza) {
        return pietanzaRepository.findById(idPietanza)
                .map(pietanzaMapper::getPietanzaDtoFromEntity)
                .orElseThrow(() -> new PietanzaNonTrovataException("pietanza con id {} non trovata", idPietanza));
    }
}
