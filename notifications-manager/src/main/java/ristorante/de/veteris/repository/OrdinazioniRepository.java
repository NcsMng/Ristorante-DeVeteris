package ristorante.de.veteris.repository;

import ristorante.de.veteris.enums.StatoOrdinazione;
import ristorante.de.veteris.model.Ordinazione;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrdinazioniRepository extends JpaRepository<Ordinazione, Integer> {
    List<Ordinazione> findAllByStatoNot(StatoOrdinazione stato);
    Optional<Ordinazione> findByUuidEquals(String uuid);
    boolean deleteByUuidEquals(String uuid);
    boolean deleteByIdEquals(Integer id);
    Optional<Ordinazione> findByIdEquals(Integer id);
}
