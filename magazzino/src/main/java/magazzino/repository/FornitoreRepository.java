package magazzino.repository;

import magazzino.model.Fornitore;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FornitoreRepository extends JpaRepository<Fornitore,Integer> {
}
