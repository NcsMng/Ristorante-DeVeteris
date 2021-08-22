package notificationsmanager.repositories;

import notificationsmanager.enums.StatoOrdinazione;
import notificationsmanager.model.Ordinazione;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface OrdinazioniRepository extends JpaRepository<Ordinazione, Integer> {
    List<Ordinazione> findAllByStatoNot(StatoOrdinazione stato);
}
