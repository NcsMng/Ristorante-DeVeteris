package com.deveteris.magazzino.repository;

import com.deveteris.magazzino.model.OrdineMateriaPrima;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.Set;

public interface OrdineMateriaPrimaRepository extends JpaRepository<OrdineMateriaPrima,Integer> {
    Set<OrdineMateriaPrima> findAllByOrdine_Id(Integer id);
    Optional<OrdineMateriaPrima> findByFornitore_IdAndOrdine_IdAndMateriaPrima_Id(Integer fornitoreId, Integer ordineId, String materiaPrimaId);
}
