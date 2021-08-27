package com.deveteris.magazzino.response;

import com.deveteris.magazzino.model.Ordine;
import dto.OrdineDto;

import java.util.HashSet;
import java.util.Set;

public class ManipulateOrdineMateriePrimeResponse {
    private OrdineDto ordine;
    private Set<String> idMPNotFound = new HashSet<>();

    public ManipulateOrdineMateriePrimeResponse(OrdineDto ordine, Set<String> idMPNotFound) {
        this.ordine = ordine;
        this.idMPNotFound = idMPNotFound;
    }

    public ManipulateOrdineMateriePrimeResponse() {
    }

    public OrdineDto getOrdine() {
        return ordine;
    }

    public void setOrdine(OrdineDto ordine) {
        this.ordine = ordine;
    }

    public Set<String> getIdMPNotFound() {
        return idMPNotFound;
    }

    public void setIdMPNotFound(Set<String> idMPNotFound) {
        this.idMPNotFound = idMPNotFound;
    }
}
