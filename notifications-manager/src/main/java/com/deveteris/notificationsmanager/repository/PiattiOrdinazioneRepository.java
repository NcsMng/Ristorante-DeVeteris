package com.deveteris.notificationsmanager.repository;

import com.deveteris.notificationsmanager.model.PiattoOrdinazione;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PiattiOrdinazioneRepository extends JpaRepository<PiattoOrdinazione,Integer> {
    Optional<PiattoOrdinazione> findByCodicePiatto(String codicePiatto);
}
