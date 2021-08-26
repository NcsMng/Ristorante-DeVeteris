package com.deveteris.notificationsmanager.model;

import com.deveteris.notificationsmanager.enums.StatoOrdinazione;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity(name = "ordinazioni")
public class Ordinazione extends BaseEntity {

    @Id
    private Integer id;

    @Column(name = "note", length = 500)
    private String note;

    @Column(name = "uuid")
    private String uuid = UUID.randomUUID().toString();

    @Column(name = "costo",precision = 2)
    private Double costo;

    @Column(name = "stato", nullable = false)
    @Enumerated(EnumType.STRING)
    private StatoOrdinazione stato = StatoOrdinazione.IN_ATTESA;

    @OneToMany(mappedBy="ordinazione")
    private Set<PiattoOrdinazione> piattiOrdinazione = new HashSet<>();


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

    public void setUuid(String uuid) {
        this.uuid = uuid;
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

    public Set<PiattoOrdinazione> getPiattiOrdinazione() {
        return piattiOrdinazione;
    }

    public void setPiattiOrdinazione(Set<PiattoOrdinazione> piattiOrdinazione) {
        this.piattiOrdinazione = piattiOrdinazione;
    }
}
