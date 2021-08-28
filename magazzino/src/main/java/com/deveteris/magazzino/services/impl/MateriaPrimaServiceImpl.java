package com.deveteris.magazzino.services.impl;

import com.deveteris.magazzino.dto.MateriaPrimaDto;
import com.deveteris.magazzino.dto.PrevisioneFabbisognoMpDto;
import com.deveteris.magazzino.exceptions.MateriaPrimaNonTrovataException;
import com.deveteris.magazzino.mapper.MateriaPrimaMapper;
import com.deveteris.magazzino.mapper.PrevisioneFabbisognoMpMapper;
import com.deveteris.magazzino.model.MateriaPrima;
import com.deveteris.magazzino.model.PrevisioneFabbisognoMp;
import com.deveteris.magazzino.repository.MateriaPrimaRepository;
import com.deveteris.magazzino.repository.PrevisioneFabbisognoMpRepository;
import com.deveteris.magazzino.requests.MateriaNonConsumataRequest;
import com.deveteris.magazzino.requests.MateriaPrimaRequest;
import com.deveteris.magazzino.services.MateriaPrimaService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class MateriaPrimaServiceImpl implements MateriaPrimaService {
    private final MateriaPrimaMapper materiaPrimaMapper;
    private final MateriaPrimaRepository materiaPrimaRepository;
    private final PrevisioneFabbisognoMpRepository previsioneFabbisognoMpRepository;
    private final PrevisioneFabbisognoMpMapper previsioneFabbisognoMpMapper;

    public MateriaPrimaServiceImpl(MateriaPrimaMapper materiaPrimaMapper, MateriaPrimaRepository materiaPrimaRepository, PrevisioneFabbisognoMpRepository previsioneFabbisognoMpRepository, PrevisioneFabbisognoMpMapper previsioneFabbisognoMpMapper) {
        this.materiaPrimaMapper = materiaPrimaMapper;
        this.materiaPrimaRepository = materiaPrimaRepository;
        this.previsioneFabbisognoMpRepository = previsioneFabbisognoMpRepository;
        this.previsioneFabbisognoMpMapper = previsioneFabbisognoMpMapper;
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
    @Transactional
    public void deleteMateriaPrima(String id) {
        materiaPrimaRepository.deleteById(id);
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

    @Override
    public Set<PrevisioneFabbisognoMpDto> insertMateriaPrimaNonConsumataFineMese(Set<MateriaNonConsumataRequest> request) {
        return request.stream().map(materiaNonConsumata->{
            PrevisioneFabbisognoMp previsioneFabbisognoMp = previsioneFabbisognoMpRepository
                    .findByMeseEqualsAndMateriaPrima_Id(materiaNonConsumata.getMese(), materiaNonConsumata.getIdMateriaPrima())
                    .orElseThrow(() -> new MateriaPrimaNonTrovataException("Materia prima con id {} e mese {} non trovata", materiaNonConsumata.getIdMateriaPrima(), materiaNonConsumata.getMese()));
            previsioneFabbisognoMp.setQtaNonUsata(materiaNonConsumata.getQuantitaNonUsata());
            return previsioneFabbisognoMpMapper.getDtoFromEntity(previsioneFabbisognoMpRepository.save(previsioneFabbisognoMp),materiaPrimaMapper);
        }).collect(Collectors.toSet());
    }
}
