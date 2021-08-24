package ristorante.de.veteris.services.impl;

import ristorante.de.veteris.dto.PietanzaRequest;
import ristorante.de.veteris.exception.PietanzaNonTrovataException;
import ristorante.de.veteris.mapper.PietanzaMapper;
import ristorante.de.veteris.model.Pietanza;
import ristorante.de.veteris.repository.PietanzaRepository;
import ristorante.de.veteris.services.PietanzeService;
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
