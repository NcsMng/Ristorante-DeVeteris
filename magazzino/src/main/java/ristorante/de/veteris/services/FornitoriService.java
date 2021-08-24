package ristorante.de.veteris.services;

import ristorante.de.veteris.model.Fornitore;
import ristorante.de.veteris.requests.FornitoreRequest;

public interface FornitoriService {
    Fornitore persistFornitore(FornitoreRequest pietanzaRequest);
    boolean deleteFornitore(Integer id);
}
