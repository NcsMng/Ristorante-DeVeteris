package notificationsmanager.repository;

import notificationsmanager.enums.StatoOrdinazione;
import notificationsmanager.model.Ordinazione;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrdinazioniRepository extends JpaRepository<Ordinazione, Integer> {
    List<Ordinazione> findAllByStatoNot(StatoOrdinazione stato);
    Optional<Ordinazione> findByUuidEquals(String uuid);
    boolean deleteByUuidEquals(String uiid);
    boolean deleteByIdEquals(Integer id);
}
