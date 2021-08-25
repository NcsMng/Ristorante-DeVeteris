package com.deveteris.magazzino.services.impl;

import com.deveteris.magazzino.mapper.MateriaPrimaMapper;
import com.deveteris.magazzino.model.MateriaPrima;
import com.deveteris.magazzino.exceptions.MateriaPrimaNonTrovataException;
import com.deveteris.magazzino.repository.MateriaPrimaRepository;
import com.deveteris.magazzino.requests.MateriaPrimaRequest;
import com.deveteris.magazzino.services.MateriaPrimaService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

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
    public MateriaPrima persistMateriaPrima(MateriaPrimaRequest materiaPrimaRequest) {
        if (StringUtils.isNotBlank(materiaPrimaRequest.getId())) {
            MateriaPrima materiaPrima = materiaPrimaRepository.findById(materiaPrimaRequest.getId())
                    .orElseThrow(() -> new MateriaPrimaNonTrovataException("Materia con Id {} non trovata!", materiaPrimaRequest.getId()));
            return materiaPrimaMapper.updateMateriaPrimaFromRequest(materiaPrima, materiaPrimaRequest);
        } else {
            return materiaPrimaRepository
                    .save(materiaPrimaMapper.getEntityFromRequest(materiaPrimaRequest));
        }
    }

    @Override
    public boolean deleteMateriaPrima(String id) {
        return materiaPrimaRepository.deleteByIdEquals(id);
    }
}
