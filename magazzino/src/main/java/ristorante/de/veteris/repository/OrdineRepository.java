package ristorante.de.veteris.repository;

import ristorante.de.veteris.model.Ordine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdineRepository extends JpaRepository<Ordine,Integer> {
}
