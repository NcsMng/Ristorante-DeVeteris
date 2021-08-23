package magazzino.repository;

import magazzino.model.Ordine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdineRepository extends JpaRepository<Ordine,Integer> {
}
