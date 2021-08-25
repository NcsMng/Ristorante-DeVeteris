package com.deveteris.magazzino.services;

import com.deveteris.magazzino.model.Fornitore;
import com.deveteris.magazzino.requests.FornitoreRequest;

public interface FornitoriService {
    Fornitore persistFornitore(FornitoreRequest pietanzaRequest);
    boolean deleteFornitore(Integer id);
}
