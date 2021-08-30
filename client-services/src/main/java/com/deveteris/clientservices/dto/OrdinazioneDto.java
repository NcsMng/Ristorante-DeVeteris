package com.deveteris.clientservices.dto;

import com.deveteris.notificationsmanager.enums.StatoOrdinazione;

import java.util.Objects;
import java.util.Set;

public class OrdinazioneDto {
    private Integer id;
    private String note;
    private String uuid;
    private Double costo;
    private StatoOrdinazione stato;
    private Set<PiattoOrdinazioneDto> piattiOrdinazione;

    public OrdinazioneDto(Integer id, String note, Double costo, StatoOrdinazione stato, Set<PiattoOrdinazioneDto> piattiOrdinazione, String uuid) {
        this.id = id;
        this.note = note;
        this.costo = costo;
        this.stato = stato;
        this.uuid = uuid;
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

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrdinazioneDto that = (OrdinazioneDto) o;
        return Objects.equals(id, that.id) && Objects.equals(note, that.note) && Objects.equals(uuid, that.uuid) && Objects.equals(costo, that.costo) && stato == that.stato && Objects.equals(piattiOrdinazione, that.piattiOrdinazione);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, note, uuid, costo, stato, piattiOrdinazione);
    }
}
