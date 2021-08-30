package com.deveteris.permessi.repository;

import com.deveteris.permessi.model.Utente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UtenteRepository extends JpaRepository<Utente,Integer> {
    Utente findByCodiceUtenteEquals(String codiceUtente);
}
