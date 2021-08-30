package com.deveteris.magazzino.requests;

import java.util.List;

public class OrdineMateriaPrimaRequest {
    private Integer idOrdine; //idOrdine
    private Integer idFornitore;
    private List<MpQtaDto> idMateriePrimeQta;

    public OrdineMateriaPrimaRequest(Integer idOrdine, Integer idFornitore, List<MpQtaDto> idMateriePrimeQta) {
        this.idOrdine = idOrdine;
        this.idFornitore = idFornitore;
        this.idMateriePrimeQta = idMateriePrimeQta;
    }

    public OrdineMateriaPrimaRequest() {
    }


    public Integer getIdOrdine() {
        return idOrdine;
    }

    public void setIdOrdine(Integer idOrdine) {
        this.idOrdine = idOrdine;
    }

    public Integer getIdFornitore() {
        return idFornitore;
    }

    public void setIdFornitore(Integer idFornitore) {
        this.idFornitore = idFornitore;
    }

    public List<MpQtaDto> getIdMateriePrimeQta() {
        return idMateriePrimeQta;
    }

    public void setIdMateriePrimeQta(List<MpQtaDto> idMateriePrimeQta) {
        this.idMateriePrimeQta = idMateriePrimeQta;
    }
}
