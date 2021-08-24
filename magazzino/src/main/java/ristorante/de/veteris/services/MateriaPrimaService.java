package ristorante.de.veteris.services;

import ristorante.de.veteris.model.MateriaPrima;
import ristorante.de.veteris.requests.MateriaPrimaRequest;

public interface MateriaPrimaService {
    MateriaPrima persistMateriaPrima(MateriaPrimaRequest pietanzaRequest);
    boolean deleteMateriaPrima(String id);
}
