package ristorante.de.veteris.services.impl;

import ristorante.de.veteris.exceptions.FornitoreNonTrovatoException;
import ristorante.de.veteris.mapper.FornitoreMapper;
import ristorante.de.veteris.model.Fornitore;
import ristorante.de.veteris.repository.FornitoreRepository;
import ristorante.de.veteris.requests.FornitoreRequest;
import ristorante.de.veteris.services.FornitoriService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class FornitoriServiceImpl implements FornitoriService {
    private final FornitoreRepository fornitoreRepository;
    private final FornitoreMapper fornitoreMapper;

    public FornitoriServiceImpl(FornitoreRepository fornitoreRepository, FornitoreMapper fornitoreMapper) {
        this.fornitoreRepository = fornitoreRepository;
        this.fornitoreMapper = fornitoreMapper;
    }

    @Override
    @Transactional
    public Fornitore persistFornitore(FornitoreRequest fornitoreRequest) {
        return Optional.ofNullable(fornitoreRequest.getId())
                .map(fornitoreId ->
                {
                    Fornitore fornitore = fornitoreRepository.findById(fornitoreId)
                            .orElseThrow(() -> new FornitoreNonTrovatoException("Fornitore con id {} non trovata", fornitoreId));
                    return fornitoreMapper.updateFornitoreFromRequest(fornitore, fornitoreRequest);
                })
                .orElseGet(() -> fornitoreRepository
                        .save(fornitoreMapper.getEntityFromRequest(fornitoreRequest)));
    }

    @Override
    public boolean deleteFornitore(Integer id) {
        return false;
    }
}
