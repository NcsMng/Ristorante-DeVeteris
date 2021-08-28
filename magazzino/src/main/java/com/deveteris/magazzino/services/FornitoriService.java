package com.deveteris.magazzino.services;

import com.deveteris.magazzino.dto.FornitoreDto;
import com.deveteris.magazzino.requests.FornitoreRequest;

import java.util.Set;

public interface FornitoriService {
    /**
     * salvataggio o modifica di fornitori
     * @param pietanzaRequest richiesta per la modifica dei fornitori
     * @return FornitoreDto rappresentante del fornitore salvato o modificato
     */
    FornitoreDto persistFornitore(FornitoreRequest pietanzaRequest);

    /**
     * cancellazione fornitore per id passato
     * @param id id del fornitore da cancellare
     */
    void deleteFornitore(Integer id);

    /**
     * recupero di tutti i fornitori
     * @return set FornitoreDto rappresentanti i fornitori
     */
    Set<FornitoreDto> getAllFornitori();

    /**
     * recupero di un fornitore per id
     * @param id fornitore da recuperare
     * @return FornitoreDto rappresentante il fornitore recuperato
     */
    FornitoreDto getFornitoreById(Integer id);
}
