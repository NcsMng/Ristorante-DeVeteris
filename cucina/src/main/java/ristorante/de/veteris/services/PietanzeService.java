package ristorante.de.veteris.services;

import ristorante.de.veteris.dto.PietanzaRequest;
import ristorante.de.veteris.model.Pietanza;

public interface PietanzeService {

    Pietanza persistPietanza(PietanzaRequest pietanzaRequest);
    boolean deletePietanza(String id);
}
