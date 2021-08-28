package com.deveteris.magazzino.services;

import com.deveteris.magazzino.dto.MateriaPrimaDto;
import com.deveteris.magazzino.dto.PrevisioneFabbisognoMpDto;
import com.deveteris.magazzino.requests.MateriaNonConsumataRequest;
import com.deveteris.magazzino.requests.MateriaPrimaRequest;

import java.util.Set;

public interface MateriaPrimaService {
    MateriaPrimaDto persistMateriaPrima(MateriaPrimaRequest pietanzaRequest);
    void deleteMateriaPrima(String id);
    Set<MateriaPrimaDto> getAllMateriePrime();
    MateriaPrimaDto getMateriaPrimaById(String id);
    Set<PrevisioneFabbisognoMpDto> insertMateriaPrimaNonConsumataFineMese(Set<MateriaNonConsumataRequest> request);

}
