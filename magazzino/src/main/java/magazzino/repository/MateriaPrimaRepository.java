package magazzino.repository;

import magazzino.model.MateriaPrima;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MateriaPrimaRepository extends JpaRepository<MateriaPrima,String> {
    boolean deleteByIdEquals(String id);
}
