package magazzino.services;

import magazzino.model.Ordine;
import magazzino.requests.OrdineMateriaPrimaRequest;
import magazzino.requests.OrdineRequest;
import magazzino.response.ManipulateOrdineMateriePrimeResponse;

public interface OrdiniService {

    Ordine persistOrdine(OrdineRequest ordine);
    ManipulateOrdineMateriePrimeResponse manipulateOrdineMateriePrime(OrdineMateriaPrimaRequest ordineMateriaPrimaRequest);
}
