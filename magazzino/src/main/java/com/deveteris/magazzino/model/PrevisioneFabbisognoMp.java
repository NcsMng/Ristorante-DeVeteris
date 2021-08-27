package com.deveteris.magazzino.model;

import javax.persistence.*;

@Entity(name = "previsione_fabbisogno_mp")
public class PrevisioneFabbisognoMp {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "mese")
    private Integer mese;

    @Column(name = "quantita")
    private Double quantita;

    @Column(name = "qta_non_usata")
    private Double qtaNonUsata;

    @ManyToOne
    @JoinColumn(name="id_materia_prima", nullable=false)
    private MateriaPrima materiaPrima;

    public PrevisioneFabbisognoMp() {
    }

    public PrevisioneFabbisognoMp(Integer id, Integer mese, Double quantita, Double qtaNonUsata, MateriaPrima materiaPrima) {
        this.id = id;
        this.mese = mese;
        this.quantita = quantita;
        this.qtaNonUsata = qtaNonUsata;
        this.materiaPrima = materiaPrima;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMese() {
        return mese;
    }

    public void setMese(Integer mese) {
        this.mese = mese;
    }

    public Double getQuantita() {
        return quantita;
    }

    public void setQuantita(Double quantita) {
        this.quantita = quantita;
    }

    public Double getQtaNonUsata() {
        return qtaNonUsata;
    }

    public void setQtaNonUsata(Double qtaNonUsata) {
        this.qtaNonUsata = qtaNonUsata;
    }

    public MateriaPrima getMateriaPrima() {
        return materiaPrima;
    }

    public void setMateriaPrima(MateriaPrima materiaPrima) {
        this.materiaPrima = materiaPrima;
    }
}
