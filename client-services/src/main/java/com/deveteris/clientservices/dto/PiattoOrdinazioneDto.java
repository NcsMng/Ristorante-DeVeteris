package com.deveteris.clientservices.dto;

public class PiattoOrdinazioneDto {
    private Integer id;
    private String codicePiatto;
    private Integer quantita;
    private String note;

    public PiattoOrdinazioneDto(Integer id, String codicePiatto, Integer quantita, String note) {
        this.id = id;
        this.codicePiatto = codicePiatto;
        this.quantita = quantita;
        this.note = note;
    }
    public PiattoOrdinazioneDto(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
