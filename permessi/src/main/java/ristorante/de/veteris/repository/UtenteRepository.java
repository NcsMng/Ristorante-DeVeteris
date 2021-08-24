package ristorante.de.veteris.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ristorante.de.veteris.model.Utente;

import java.util.Optional;

public interface UtenteRepository extends JpaRepository<Utente,Integer> {
    Optional<Utente> getByCodiceUtenteEquals(String codiceUtente);
}
