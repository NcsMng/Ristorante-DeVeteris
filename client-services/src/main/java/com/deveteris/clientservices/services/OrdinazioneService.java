package com.deveteris.clientservices.services;

import com.deveteris.clientservices.dto.OrdinazioneDto;
import com.deveteris.clientservices.request.OrdinazioneRequest;

import java.util.List;

public interface OrdinazioneService {

    /**
     * salvataggio o modifica ordinazioni clienti
     * @param ordinazione ordinazione da salvare contenente i dati principali
     * @param uuidOrdine uuid ordinazione che si vuole modificare. Uuid necessario per evitare che chiamate da parte
     *                   di clienti con id di ordini auto-incrementali modifichino ordini di altre persone
     * @return OrdinazioneDto dto che rappresenta l'ordine salvato contenente l'uuid dell'ordine per permettere operazioni
     * di modifica, cancellazione, recupero
     */
    OrdinazioneDto persistOrdinazione(OrdinazioneRequest ordinazione, String uuidOrdine);

    /**
     * recupero ordine cliente per uuid.
     * @param uuid necessario per evitare che chiamate da clienti con id ordini auto-incrementali
     *       recuperino ordini di altri clienti
     * @return ordinazione recuperata dall'uuid passato
     */
    OrdinazioneDto getOrdine(String uuid);

    /**
     * cancellazione ordine per uuid.
     * @param uuidOrdine necessario per evitare che chiamate da clienti con id ordini auto-incrementali
     *             recuperino ordini di altri clienti
     * @return boolean che indica l'effettiva cancellazione
     */
    boolean deleteOrdinazione(String uuidOrdine);

    /**
     * salvataggio o modifica ordinazioni staff
     * @param ordinazione ordinazione da salvare contenente i dati principali
     * @return OrdinazioneDto dto che rappresenta l'ordine salvato
     */
    OrdinazioneDto persistOrdinazione(OrdinazioneRequest ordinazione);

    /**
     * recupero ordine cliente per id.
     * @param id id dell'ordine da recuperare
     * @return ordinazione recuperata dall'id passato
     */
    OrdinazioneDto getOrdine(Integer id);

    /**
     * recupero di tutte gli ordini presenti
     * @return lista degli ordini presenti
     */
    List<OrdinazioneDto> getAllOrdini();

    /**
     * cancellazione ordini per id
     * @param id id dell'ordine da cancellare
     * @return boolean che indica l'effettiva cancellazione
     */
    boolean deleteOrdinazione(Integer id);

}
