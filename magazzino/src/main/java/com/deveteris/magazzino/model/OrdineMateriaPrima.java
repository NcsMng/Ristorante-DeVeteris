package com.deveteris.magazzino.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "ordini_materia_prima")
public class OrdineMateriaPrima implements Serializable {
    static final long serialVersionUID = 5123213L;
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "quantita_ordinata")
    private Double quantitaOrdinata;

    @ManyToOne
    @JoinColumn(name = "id_ordine")
    @JsonManagedReference
    private Ordine ordine;

    @ManyToOne
    @JoinColumn(name = "id_materia_prima")
    @JsonManagedReference
    private MateriaPrima materiaPrima;

    @ManyToOne
    @JoinColumn(name = "id_fornitore")
    @JsonManagedReference
    private Fornitore fornitore;

    public OrdineMateriaPrima(Integer id, Double quantitaOrdinata, Ordine ordine, MateriaPrima materiaPrima, Fornitore fornitore) {
        this.id = id;
        this.quantitaOrdinata = quantitaOrdinata;
        this.ordine = ordine;
        this.materiaPrima = materiaPrima;
        this.fornitore = fornitore;
    }

    public OrdineMateriaPrima() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getQuantitaOrdinata() {
        return quantitaOrdinata;
    }

    public void setQuantitaOrdinata(Double quantitaOrdinata) {
        this.quantitaOrdinata = quantitaOrdinata;
    }

    public Ordine getOrdine() {
        return ordine;
    }

    public void setOrdine(Ordine ordine) {
        this.ordine = ordine;
    }

    public MateriaPrima getMateriaPrima() {
        return materiaPrima;
    }

    public void setMateriaPrima(MateriaPrima materiaPrima) {
        this.materiaPrima = materiaPrima;
    }

    public Fornitore getFornitore() {
        return fornitore;
    }

    public void setFornitore(Fornitore fornitore) {
        this.fornitore = fornitore;
    }
}
