package com.deveteris.magazzino.services;

import com.deveteris.magazzino.response.ManipulateOrdineMateriePrimeResponse;
import com.deveteris.magazzino.requests.OrdineMateriaPrimaRequest;
import com.deveteris.magazzino.requests.OrdineRequest;
import dto.OrdineDto;

import java.util.Set;

public interface OrdiniService {

    OrdineDto persistOrdine(OrdineRequest ordine);
    ManipulateOrdineMateriePrimeResponse manipulateOrdineMateriePrime(OrdineMateriaPrimaRequest ordineMateriaPrimaRequest);
    Set<OrdineDto> getAllOrdini();
    OrdineDto getOrdineById(Integer id);
    void deleteOrdineById(Integer id);
    void analizeOrdiniAnnoPrecedente();
}
