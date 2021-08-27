package com.deveteris.magazzino.repository;

import com.deveteris.magazzino.model.Ordine;
import com.deveteris.magazzino.model.OrdineMateriaPrima;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface OrdineMateriaPrimaRepository extends JpaRepository<OrdineMateriaPrima,Integer> {
    Set<OrdineMateriaPrima> findAllByOrdine(Ordine ordine);
}
