package com.deveteris.cucina.services.impl;

import com.deveteris.cucina.exception.PietanzaNonTrovataException;
import com.deveteris.cucina.model.Pietanza;
import com.deveteris.cucina.repository.PietanzaRepository;
import com.deveteris.cucina.services.PietanzeService;
import com.deveteris.cucina.request.PietanzaRequest;
import com.deveteris.cucina.mapper.PietanzaMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

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
    @Transactional
    public Pietanza persistPietanza(PietanzaRequest pietanzaRequest) {
        return Optional.ofNullable(pietanzaRequest.getId())
                .map(pietanzaId ->
                {
                    Pietanza pietanze = pietanzaRepository.findById(pietanzaId)
                            .orElseThrow(() -> new PietanzaNonTrovataException("Pietanza con id {} non trovata", pietanzaId));
                    return pietanzaMapper.updatePietanzaFromRequest(pietanze, pietanzaRequest);
                })
                .orElseGet(() -> pietanzaRepository
                        .save(pietanzaMapper.getEntityFromRequest(pietanzaRequest)));
    }

    //Metodo solo per chef e manager
    @Override
    public boolean deletePietanza(String id) {
        return pietanzaRepository.deleteByIdEquals(id);
    }
}
