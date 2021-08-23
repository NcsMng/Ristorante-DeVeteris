package cucina.services;

import cucina.dto.PietanzaRequest;
import cucina.model.Pietanza;

public interface PietanzeService {

    Pietanza persistPietanza(PietanzaRequest pietanzaRequest);
    boolean deletePietanza(String id);
}
