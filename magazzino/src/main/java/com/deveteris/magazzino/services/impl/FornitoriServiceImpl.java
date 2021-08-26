package com.deveteris.magazzino.services.impl;

import com.deveteris.magazzino.exceptions.FornitoreNonTrovatoException;
import com.deveteris.magazzino.mapper.FornitoreMapper;
import com.deveteris.magazzino.model.Fornitore;
import com.deveteris.magazzino.repository.FornitoreRepository;
import com.deveteris.magazzino.requests.FornitoreRequest;
import com.deveteris.magazzino.services.FornitoriService;
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
    public Fornitore persistFornitore(FornitoreRequest fornitoreRequest) {
        Fornitore fornitoreEntity = Optional.ofNullable(fornitoreRequest.getId())
                .map(fornitoreId ->
                {
                    Fornitore fornitore = fornitoreRepository.findById(fornitoreId)
                            .orElseThrow(() -> new FornitoreNonTrovatoException("Fornitore con id {} non trovata", fornitoreId));
                    return fornitoreMapper.updateFornitoreFromRequest(fornitore, fornitoreRequest);
                })
                .orElseGet(() -> fornitoreMapper.getEntityFromRequest(fornitoreRequest));
        return fornitoreRepository.save(fornitoreEntity);
    }

    @Override
    public boolean deleteFornitore(Integer id) {
        return false;
    }
}
