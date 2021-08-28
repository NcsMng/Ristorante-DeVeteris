package com.deveteris.magazzino.services;

import com.deveteris.magazzino.dto.OrdineDto;
import com.deveteris.magazzino.requests.OrdineMateriaPrimaRequest;
import com.deveteris.magazzino.requests.OrdineRequest;
import com.deveteris.magazzino.response.ManipulateOrdineMateriePrimeResponse;

import java.util.Set;

public interface OrdiniService {

    /**
     * salvataggio o modifica di ordini di materia prima
     * @param ordine ordine contenente i dati per salvataggio o modifica dell'ordine
     * @return OrdineDto rappresentante l'ordine di materia prima salvato o modificato
     */
    OrdineDto persistOrdine(OrdineRequest ordine);

    /**
     * Modifica o aggiunta di materia prima ordinata ad un dato ordine. Verranno aggiunte materie prime all'ordine oppure
     * aumentante le quantita se la materia prima e' gia' presente all'interno dell'ordine
     * @param ordineMateriaPrimaRequest request contenente tutti i campi possibilmente modificabili o salvabili per una
     *                                  data ordinazione
     * @return ManipulateOrdineMateriePrimeResponse contenente l'ordine modificato o salvato e le materie prime che non
     * sono presenti nel db e che quindi non sono possibili da rifornire.
     */
    ManipulateOrdineMateriePrimeResponse manipulateOrdineMateriePrime(OrdineMateriaPrimaRequest ordineMateriaPrimaRequest);

    /**
     * recupero di tutti gli ordini verso fornitori di materie prime
     * @return set OrdiniDto rappresentanti gli ordini
     */
    Set<OrdineDto> getAllOrdini();

    /**
     * recupero di un ordine per id
     * @param id id dell'ordine da recuperare
     * @return OrdineDto rappresentante l'ordine recuperato
     */
    OrdineDto getOrdineById(Integer id);

    /**
     * cancellazione di un ordine per il suo id
     * @param id ordine da cancellare
     */
    void deleteOrdineById(Integer id);

    /**
     * creazione di previsioni di quantita materia prima per mese per ogni materia prima utili per l'anno successivo.
     * Questo metodo e' schedulato e viene eseguito ogni inizio anno
     */
    void analizeOrdiniAnnoPrecedente();
}
