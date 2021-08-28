package com.deveteris.magazzino.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "materia_prima")
public class MateriaPrima extends BaseEntity implements Serializable {
    static final long serialVersionUID = 223532324L;
    @Id
    private String id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "quantita",precision = 2)
    private Double quantita;

    @Column(name = "descrizione")
    private String descrizione;

    @OneToMany(mappedBy = "materiaPrima",fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonBackReference
    private Set<OrdineMateriaPrima> ordiniMateriaPrima = new HashSet<>();

    @OneToMany(mappedBy = "materiaPrima", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<PrevisioneFabbisognoMp> previsioniFabbisognoMensile = new HashSet<>();

    public MateriaPrima(String id, String nome, Double quantita, String descrizione, Set<OrdineMateriaPrima> ordiniMateriaPrima, Set<PrevisioneFabbisognoMp> previsioniFabbisognoMensile) {
        this.id = id;
        this.nome = nome;
        this.quantita = quantita;
        this.descrizione = descrizione;
        this.ordiniMateriaPrima = ordiniMateriaPrima;
        this.previsioniFabbisognoMensile = previsioniFabbisognoMensile;
    }

    public MateriaPrima() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getQuantita() {
        return quantita;
    }

    public void setQuantita(Double quantita) {
        this.quantita = quantita;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public Set<OrdineMateriaPrima> getOrdiniMateriaPrima() {
        return ordiniMateriaPrima;
    }

    public void setOrdiniMateriaPrima(Set<OrdineMateriaPrima> ordiniMateriaPrima) {
        this.ordiniMateriaPrima = ordiniMateriaPrima;
    }

    public Set<PrevisioneFabbisognoMp> getPrevisioniFabbisognoMensile() {
        return previsioniFabbisognoMensile;
    }

    public void setPrevisioniFabbisognoMensile(Set<PrevisioneFabbisognoMp> previsioniFabbisognoMensile) {
        this.previsioniFabbisognoMensile = previsioniFabbisognoMensile;
    }
}
