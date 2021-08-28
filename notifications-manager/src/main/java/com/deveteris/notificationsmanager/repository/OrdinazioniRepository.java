package com.deveteris.notificationsmanager.repository;

import com.deveteris.notificationsmanager.enums.StatoOrdinazione;
import com.deveteris.notificationsmanager.model.Ordinazione;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrdinazioniRepository extends JpaRepository<Ordinazione, Integer> {
    List<Ordinazione> findAllByStatoNot(StatoOrdinazione stato);
    Optional<Ordinazione> findByUuidEquals(String uuid);
    int deleteByUuidEquals(String uuid);
    Integer deleteByIdEquals(Integer id);
    Optional<Ordinazione> findByIdEquals(Integer id);
}
