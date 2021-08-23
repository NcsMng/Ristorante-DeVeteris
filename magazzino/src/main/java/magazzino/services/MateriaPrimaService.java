package magazzino.services;

import magazzino.model.MateriaPrima;
import magazzino.requests.MateriaPrimaRequest;

public interface MateriaPrimaService {
    MateriaPrima persistMateriaPrima(MateriaPrimaRequest pietanzaRequest);
    boolean deleteMateriaPrima(String id);
}
