package com.deveteris.magazzino.repository;

import com.deveteris.magazzino.model.Ordine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.Set;

public interface OrdineRepository extends JpaRepository<Ordine,Integer> {

    @Query("select ordine from ordini ordine where ordine.dataConsegna >=:startDate and ordine.dataConsegna <=:endDate")
    public Set<Ordine> getOrdiniPerMeseAnnoPassato(@Param("startDate") Date startDate, @Param("endDate") Date endDate);
}
