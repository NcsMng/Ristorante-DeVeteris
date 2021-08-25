package com.deveteris.magazzino.services;

import com.deveteris.magazzino.model.MateriaPrima;
import com.deveteris.magazzino.requests.MateriaPrimaRequest;

public interface MateriaPrimaService {
    MateriaPrima persistMateriaPrima(MateriaPrimaRequest pietanzaRequest);
    boolean deleteMateriaPrima(String id);
}
