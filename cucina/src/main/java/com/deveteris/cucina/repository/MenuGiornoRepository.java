package com.deveteris.cucina.repository;

import com.deveteris.cucina.model.PiattoMenuGiorno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface MenuGiornoRepository extends JpaRepository<PiattoMenuGiorno,Integer> {

    @Modifying
    @Query("delete from menu_giorno mg where mg.pietanza.id=:idPiatto")
    @Transactional
    Integer deleteByPietanzaId(@Param("idPiatto") String idPiatto);


}
