package com.deveteris.cucina.services;

import com.deveteris.cucina.dto.PietanzaDto;
import com.deveteris.cucina.request.PietanzaRequest;

import java.util.Set;

public interface PietanzeService {

    /**
     * salvataggio o modifica pietanze dal menu generico
     * @param pietanzaRequest pietanza da salvare o modificare(passando l'id)
     * @return PietanzaDto dto che rappresenta la pietanza salvata
     */
    PietanzaDto persistPietanza(PietanzaRequest pietanzaRequest);

    /**
     * cancellazione pietanza dal menu
     * @param id id pietanza che si vuole cancellare dal menu generico
     */
    void deletePietanza(String id);

    /**
     * recupero di tutte le pietanze nel menu generico
     * @return il set di tutte le pietanza presenti
     */
    Set<PietanzaDto> getAllPietanze();

    /**
     * recupero pietanza per il suo id piatto
     * @param idPietanza
     * @return PietanzaDto dto che rappresenta la pietanza trovata
     */
    PietanzaDto getPietanzaById(String idPietanza);
}
