package com.deveteris.clientservices.request;

public class PiattoOrdinazioneRequest {
    private String codicePiatto;
    private Integer quantita;

    private String note;

    public PiattoOrdinazioneRequest(String codicePiatto, Integer quantita, String note) {
        this.codicePiatto = codicePiatto;
        this.quantita = quantita;
        this.note = note;
    }

    public PiattoOrdinazioneRequest() {
    }

    public String getCodicePiatto() {
        return codicePiatto;
    }

    public void setCodicePiatto(String codicePiatto) {
        this.codicePiatto = codicePiatto;
    }

    public Integer getQuantita() {
        return quantita;
    }

    public void setQuantita(Integer quantita) {
        this.quantita = quantita;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}

