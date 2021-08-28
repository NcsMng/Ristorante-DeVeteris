package com.deveteris.magazzino.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "fornitori")
public class Fornitore extends BaseEntity implements Serializable {
    static final long serialVersionUID = 112321321L;
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "nome")
    private String nome;
    @Column(name = "specializzazione")
    private String specializzazione;
    @OneToMany(mappedBy = "fornitore",fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonBackReference
    private Set<OrdineMateriaPrima> ordiniMateriaPrima = new HashSet<>();

    public Fornitore(Integer id, String nome, String specializzazione, Set<OrdineMateriaPrima> ordiniMateriaPrima) {
        this.id = id;
        this.nome = nome;
        this.specializzazione = specializzazione;
        this.ordiniMateriaPrima = ordiniMateriaPrima;
    }
    public Fornitore(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSpecializzazione() {
        return specializzazione;
    }

    public void setSpecializzazione(String specializzazione) {
        this.specializzazione = specializzazione;
    }

    public Set<OrdineMateriaPrima> getOrdiniMateriaPrima() {
        return ordiniMateriaPrima;
    }

    public void setOrdiniMateriaPrima(Set<OrdineMateriaPrima> ordiniMateriaPrima) {
        this.ordiniMateriaPrima = ordiniMateriaPrima;
    }

}
