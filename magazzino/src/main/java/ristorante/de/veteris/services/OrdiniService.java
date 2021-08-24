package ristorante.de.veteris.services;

import ristorante.de.veteris.model.Ordine;
import ristorante.de.veteris.requests.OrdineMateriaPrimaRequest;
import ristorante.de.veteris.requests.OrdineRequest;
import ristorante.de.veteris.response.ManipulateOrdineMateriePrimeResponse;

public interface OrdiniService {

    Ordine persistOrdine(OrdineRequest ordine);
    ManipulateOrdineMateriePrimeResponse manipulateOrdineMateriePrime(OrdineMateriaPrimaRequest ordineMateriaPrimaRequest);
}
