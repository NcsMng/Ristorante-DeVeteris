package com.deveteris.magazzino.services.impl;

import com.deveteris.magazzino.mapper.MateriaPrimaMapper;
import com.deveteris.magazzino.model.MateriaPrima;
import com.deveteris.magazzino.exceptions.MateriaPrimaNonTrovataException;
import com.deveteris.magazzino.repository.MateriaPrimaRepository;
import com.deveteris.magazzino.requests.MateriaPrimaRequest;
import com.deveteris.magazzino.services.MateriaPrimaService;
import dto.MateriaPrimaDto;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class MateriaPrimaServiceImpl implements MateriaPrimaService {
    private final MateriaPrimaMapper materiaPrimaMapper;
    private final MateriaPrimaRepository materiaPrimaRepository;

    public MateriaPrimaServiceImpl(MateriaPrimaMapper materiaPrimaMapper, MateriaPrimaRepository materiaPrimaRepository) {
        this.materiaPrimaMapper = materiaPrimaMapper;
        this.materiaPrimaRepository = materiaPrimaRepository;
    }

    @Override
    @Transactional
    public MateriaPrimaDto persistMateriaPrima(MateriaPrimaRequest materiaPrimaRequest) {
        MateriaPrima materiaPrima = Optional.ofNullable(materiaPrimaRequest.getId())
                .map(materiaPrimaId ->
                {
                    MateriaPrima pietanze = materiaPrimaRepository.findById(materiaPrimaId)
                            .orElseGet(() -> materiaPrimaMapper.getEntityFromRequest(materiaPrimaRequest));
                    return materiaPrimaMapper.updateMateriaPrimaFromRequest(pietanze, materiaPrimaRequest);
                })
                .orElseThrow(() -> new MateriaPrimaNonTrovataException("id necessario per inserimento o modifica materiaPrima!"));
        return materiaPrimaMapper.getMateriaPrimaDtoFromEntity(materiaPrimaRepository.save(materiaPrima));
    }

    @Override
    public void deleteMateriaPrima(String id) {
        materiaPrimaRepository.deleteByIdEquals(id);
    }

    @Override
    public Set<MateriaPrimaDto> getAllMateriePrime() {
        return materiaPrimaRepository
                .findAll()
                .stream()
                .map(materiaPrimaMapper::getMateriaPrimaDtoFromEntity)
                .collect(Collectors.toSet());
    }

    @Override
    public MateriaPrimaDto getMateriaPrimaById(String id) {
        return materiaPrimaMapper.getMateriaPrimaDtoFromEntity(materiaPrimaRepository
                .findById(id)
                .orElseThrow(()-> new MateriaPrimaNonTrovataException("Materia prima con id {} non trovata", id)));
    }
}
