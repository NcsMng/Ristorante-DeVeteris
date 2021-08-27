package com.deveteris.magazzino.repository;

import com.deveteris.magazzino.model.PrevisioneFabbisognoMp;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PrevisioneFabbisognoMpRepository extends JpaRepository<PrevisioneFabbisognoMp,Integer> {

    Optional<PrevisioneFabbisognoMp> findByMeseEqualsAndMateriaPrima_Id(Integer mese, String materiaPrimaId);
}
