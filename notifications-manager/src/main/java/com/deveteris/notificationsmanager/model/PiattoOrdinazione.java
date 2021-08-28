package com.deveteris.notificationsmanager.model;

import javax.persistence.*;

@Entity(name = "piatti_ordinazione")
public class PiattoOrdinazione extends BaseEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "codice_piatto")
    private String codicePiatto;

    @Column(name = "quantita")
    private Integer quantita;

    @Column(name = "note")
    private String note;

    @ManyToOne
    @JoinColumn(name = "id_ordinazione")
    private Ordinazione ordinazione;


    public PiattoOrdinazione() {
    }

    public PiattoOrdinazione(Integer id, String codicePiatto, Integer quantita, String note, Ordinazione ordinazione) {
        this.id = id;
        this.codicePiatto = codicePiatto;
        this.quantita = quantita;
        this.note = note;
        this.ordinazione = ordinazione;
    }

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

    public Ordinazione getOrdinazione() {
        return ordinazione;
    }

    public void setOrdinazione(Ordinazione ordinazione) {
        this.ordinazione = ordinazione;
    }
}
