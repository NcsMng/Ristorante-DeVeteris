package magazzino.services;

import magazzino.model.Fornitore;
import magazzino.requests.FornitoreRequest;

public interface FornitoriService {
    Fornitore persistFornitore(FornitoreRequest pietanzaRequest);
    boolean deleteFornitore(Integer id);
}
