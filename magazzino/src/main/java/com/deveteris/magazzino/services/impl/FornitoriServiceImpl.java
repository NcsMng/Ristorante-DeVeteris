package com.deveteris.magazzino.services.impl;

import com.deveteris.magazzino.dto.FornitoreDto;
import com.deveteris.magazzino.exceptions.FornitoreNonTrovatoException;
import com.deveteris.magazzino.mapper.FornitoreMapper;
import com.deveteris.magazzino.model.Fornitore;
import com.deveteris.magazzino.repository.FornitoreRepository;
import com.deveteris.magazzino.requests.FornitoreRequest;
import com.deveteris.magazzino.services.FornitoriService;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class FornitoriServiceImpl implements FornitoriService {
    private final FornitoreRepository fornitoreRepository;
    private final FornitoreMapper fornitoreMapper;

    public FornitoriServiceImpl(FornitoreRepository fornitoreRepository, FornitoreMapper fornitoreMapper) {
        this.fornitoreRepository = fornitoreRepository;
        this.fornitoreMapper = fornitoreMapper;
    }

    @Override
    public FornitoreDto persistFornitore(FornitoreRequest fornitoreRequest) {
        Fornitore fornitoreEntity = Optional.ofNullable(fornitoreRequest.getId())
                .map(fornitoreId ->
                {
                    Fornitore fornitore = fornitoreRepository.findById(fornitoreId)
                            .orElseThrow(() -> new FornitoreNonTrovatoException("Fornitore con id {} non trovata", fornitoreId));
                    return fornitoreMapper.updateFornitoreFromRequest(fornitore, fornitoreRequest);
                })
                .orElseGet(() -> fornitoreMapper.getEntityFromRequest(fornitoreRequest));
        return fornitoreMapper.getFornitoreDtoFromEntity(fornitoreRepository.save(fornitoreEntity));
    }

    @Override
    public void deleteFornitore(Integer id) {
        fornitoreRepository.deleteById(id);
    }

    @Override
    public Set<FornitoreDto> getAllFornitori() {
        return fornitoreRepository
                .findAll()
                .stream()
                .map(fornitoreMapper::getFornitoreDtoFromEntity)
                .collect(Collectors.toSet());
    }

    @Override
    public FornitoreDto getFornitoreById(Integer id) {
        return fornitoreRepository
                .findById(id)
                .map(fornitoreMapper::getFornitoreDtoFromEntity)
                .orElseThrow(() -> new FornitoreNonTrovatoException("Fornitore con id {} non trovato", id));
    }
}
