package com.deveteris.magazzino.services;

import com.deveteris.magazzino.dto.MateriaPrimaDto;
import com.deveteris.magazzino.dto.PrevisioneFabbisognoMpDto;
import com.deveteris.magazzino.requests.MateriaNonConsumataRequest;
import com.deveteris.magazzino.requests.MateriaPrimaRequest;

import java.util.Set;

public interface MateriaPrimaService {
    /**
     * salvataggio o modifica di una materia prima
     * @param pietanzaRequest richiesta che contiene le informazioni da salvare/modificare e eventuale id
     *                        della materia priam da modificare
     * @return MateriaPrimaDto rappresentante la materia prima salvata
     */
    MateriaPrimaDto persistMateriaPrima(MateriaPrimaRequest pietanzaRequest);

    /**
     * cancellazione materia prima con id passato
     * @param id materia prima da cancellare
     */
    void deleteMateriaPrima(String id);

    /**
     * recupero di tutte le materie prime presenti nel magazzino
     * @return set MateriaPrimaDto rappresentanti le materie prime del magazzino
     */
    Set<MateriaPrimaDto> getAllMateriePrime();

    /**
     * recupero materia prima per id passato
     * @param id della materia prima da recuperare
     * @return MateriaPrimaDto rappresentante la materia prima recuperata
     */
    MateriaPrimaDto getMateriaPrimaById(String id);

    /**
     * inserimento a fine mese della quantita di materie prima ordinata ma non usate per ciascuna materia prima e mese
     * @param request contenente il mese di riferimento, l'id della materia prima per cui inserire lo spreco, e la
     *                quantita sprecata
     * @return set PrevisioneFabbisognoMpDto rappresentante le previsioni di consumo di materia prima e lo spreco con
     * cui raddrizzare le previsioni
     */
    Set<PrevisioneFabbisognoMpDto> insertMateriaPrimaNonConsumataFineMese(Set<MateriaNonConsumataRequest> request);

}
