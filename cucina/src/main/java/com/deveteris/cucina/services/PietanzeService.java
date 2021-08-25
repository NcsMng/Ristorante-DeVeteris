package com.deveteris.cucina.services;

import com.deveteris.cucina.request.PietanzaRequest;
import com.deveteris.cucina.model.Pietanza;

public interface PietanzeService {

    Pietanza persistPietanza(PietanzaRequest pietanzaRequest);
    boolean deletePietanza(String id);
}
