package ristorante.de.veteris.repository;

import ristorante.de.veteris.model.Pietanza;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PietanzaRepository extends JpaRepository<Pietanza,String> {
    boolean deleteByIdEquals(String id);
}
