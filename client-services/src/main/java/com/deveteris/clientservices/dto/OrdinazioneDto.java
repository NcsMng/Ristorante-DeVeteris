package com.deveteris.clientservices.dto;

import com.deveteris.notificationsmanager.enums.StatoOrdinazione;

import java.util.Set;
import java.util.UUID;

public class OrdinazioneDto {
    private Integer id;
    private String note;
    private final String uuid = UUID.randomUUID().toString();
    private Double costo;
    private StatoOrdinazione stato;
    private Set<PiattoOrdinazioneDto> piattiOrdinazione;

    public OrdinazioneDto(Integer id, String note, Double costo, StatoOrdinazione stato, Set<PiattoOrdinazioneDto> piattiOrdinazione) {
        this.id = id;
        this.note = note;
        this.costo = costo;
        this.stato = stato;
        this.piattiOrdinazione = piattiOrdinazione;
    }

    public OrdinazioneDto() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getUuid() {
        return uuid;
    }

    public Double getCosto() {
        return costo;
    }

    public void setCosto(Double costo) {
        this.costo = costo;
    }

    public StatoOrdinazione getStato() {
        return stato;
    }

    public void setStato(StatoOrdinazione stato) {
        this.stato = stato;
    }

    public Set<PiattoOrdinazioneDto> getPiattiOrdinazione() {
        return piattiOrdinazione;
    }

    public void setPiattiOrdinazione(Set<PiattoOrdinazioneDto> piattiOrdinazione) {
        this.piattiOrdinazione = piattiOrdinazione;
    }
}
