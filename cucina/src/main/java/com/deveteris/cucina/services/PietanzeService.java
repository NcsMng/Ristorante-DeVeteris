package com.deveteris.cucina.services;

import com.deveteris.cucina.dto.PietanzaDto;
import com.deveteris.cucina.request.PietanzaRequest;
import com.deveteris.cucina.model.Pietanza;

import java.util.Set;

public interface PietanzeService {

    PietanzaDto persistPietanza(PietanzaRequest pietanzaRequest);
    void deletePietanza(String id);
    Set<PietanzaDto> getAllPietanze();
    PietanzaDto getPietanzaById(String idPietanza);
}
