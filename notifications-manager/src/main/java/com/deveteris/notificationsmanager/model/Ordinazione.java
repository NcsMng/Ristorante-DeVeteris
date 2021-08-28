package com.deveteris.notificationsmanager.model;

import com.deveteris.notificationsmanager.enums.StatoOrdinazione;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity(name = "ordinazioni")
public class Ordinazione extends BaseEntity {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
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

    @OneToMany(mappedBy="ordinazione", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<PiattoOrdinazione> piattiOrdinazione = new HashSet<>();

    public Ordinazione(Date creationTime, Date updatedDate, Long version, Integer id, String note, String uuid, Double costo, StatoOrdinazione stato, Set<PiattoOrdinazione> piattiOrdinazione) {
        super(creationTime, updatedDate, version);
        this.id = id;
        this.note = note;
        this.uuid = uuid;
        this.costo = costo;
        this.stato = stato;
        this.piattiOrdinazione = piattiOrdinazione;
    }

    public Ordinazione(){}

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
        piattiOrdinazione.forEach(piattoOrdinazione -> piattoOrdinazione.setOrdinazione(this));
        this.piattiOrdinazione = piattiOrdinazione;
    }
}
