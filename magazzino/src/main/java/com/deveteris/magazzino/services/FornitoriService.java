package com.deveteris.magazzino.services;

import com.deveteris.magazzino.model.Fornitore;
import com.deveteris.magazzino.requests.FornitoreRequest;
import dto.FornitoreDto;

import java.util.Set;

public interface FornitoriService {
    FornitoreDto persistFornitore(FornitoreRequest pietanzaRequest);
    void deleteFornitore(Integer id);
    Set<FornitoreDto> getAllFornitori();
    FornitoreDto getFornitoreById(Integer id);
}
