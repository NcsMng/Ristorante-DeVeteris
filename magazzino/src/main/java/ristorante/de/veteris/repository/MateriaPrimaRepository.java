package ristorante.de.veteris.repository;

import ristorante.de.veteris.model.MateriaPrima;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MateriaPrimaRepository extends JpaRepository<MateriaPrima,String> {
    boolean deleteByIdEquals(String id);
}
