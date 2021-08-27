package com.deveteris.magazzino.services;

import com.deveteris.magazzino.model.MateriaPrima;
import com.deveteris.magazzino.requests.MateriaPrimaRequest;
import dto.MateriaPrimaDto;

import java.util.Set;

public interface MateriaPrimaService {
    MateriaPrimaDto persistMateriaPrima(MateriaPrimaRequest pietanzaRequest);
    void deleteMateriaPrima(String id);
    Set<MateriaPrimaDto> getAllMateriePrime();
    MateriaPrimaDto getMateriaPrimaById(String id);
}
