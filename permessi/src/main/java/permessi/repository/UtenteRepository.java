package permessi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import permessi.model.Utente;

import java.util.Optional;

public interface UtenteRepository extends JpaRepository<Utente,Integer> {
    Optional<Utente> getByCodiceUtenteEquals(String codiceUtente);
}
