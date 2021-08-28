package com.deveteris.magazzino.requests;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

public class OrdineMateriaPrimaRequest {
    private Integer idOrdine; //idOrdine
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date dataOrdinazione;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date dataConsegna;
    private Integer idFornitore;
    private List<MpQtaDto> idMateriePrimeQta;

    public OrdineMateriaPrimaRequest(Integer idOrdine, Date dataOrdinazione, Date dataConsegna, Integer idFornitore, List<MpQtaDto> idMateriePrimeQta) {
        this.idOrdine = idOrdine;
        this.dataOrdinazione = dataOrdinazione;
        this.dataConsegna = dataConsegna;
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

    public Date getDataOrdinazione() {
        return dataOrdinazione;
    }

    public void setDataOrdinazione(Date dataOrdinazione) {
        this.dataOrdinazione = dataOrdinazione;
    }

    public Date getDataConsegna() {
        return dataConsegna;
    }

    public void setDataConsegna(Date dataConsegna) {
        this.dataConsegna = dataConsegna;
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
