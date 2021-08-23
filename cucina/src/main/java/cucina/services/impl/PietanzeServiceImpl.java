package cucina.services.impl;

import cucina.dto.PietanzaRequest;
import cucina.exception.PietanzaNonTrovataException;
import cucina.mapper.PietanzaMapper;
import cucina.model.Pietanza;
import cucina.repository.PietanzaRepository;
import cucina.services.PietanzeService;
import org.springframework.stereotype.Service;

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
