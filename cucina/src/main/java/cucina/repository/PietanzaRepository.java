package cucina.repository;

import cucina.model.Pietanza;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PietanzaRepository extends JpaRepository<Pietanza,String> {
}
