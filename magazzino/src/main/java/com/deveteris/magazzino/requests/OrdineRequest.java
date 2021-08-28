package com.deveteris.magazzino.requests;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class OrdineRequest {
    private Integer id;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date dataOrdine;
    @DateTimeFormat (pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat (pattern = "yyyy-MM-dd HH:mm:ss")
    private Date dataConsegna;

    public OrdineRequest(Integer id, Date dataOrdine, Date dataConsegna) {
        this.id = id;
        this.dataOrdine = dataOrdine;
        this.dataConsegna = dataConsegna;
    }
    public OrdineRequest(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDataOrdine() {
        return dataOrdine;
    }

    public void setDataOrdine(Date dataOrdine) {
        this.dataOrdine = dataOrdine;
    }

    public Date getDataConsegna() {
        return dataConsegna;
    }

    public void setDataConsegna(Date dataConsegna) {
        this.dataConsegna = dataConsegna;
    }
}
