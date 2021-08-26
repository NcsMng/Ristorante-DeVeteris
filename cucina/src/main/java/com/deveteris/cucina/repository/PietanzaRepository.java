package com.deveteris.cucina.repository;

import com.deveteris.cucina.model.Pietanza;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PietanzaRepository extends JpaRepository<Pietanza,String> {
    Integer deleteByIdEquals(String id);
}
