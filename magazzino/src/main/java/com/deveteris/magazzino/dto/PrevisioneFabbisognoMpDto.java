package com.deveteris.magazzino.dto;

public class PrevisioneFabbisognoMpDto {
    private Integer id;
    private Integer mese;
    private Double quantita;
    private Double qtaNonUsata;
    private MateriaPrimaDto materiaPrima;

    public PrevisioneFabbisognoMpDto(Integer id, Integer mese, Double quantita, Double qtaNonUsata, MateriaPrimaDto materiaPrima) {
        this.id = id;
        this.mese = mese;
        this.quantita = quantita;
        this.qtaNonUsata = qtaNonUsata;
        this.materiaPrima = materiaPrima;
    }

    public PrevisioneFabbisognoMpDto(){

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

    public MateriaPrimaDto getMateriaPrima() {
        return materiaPrima;
    }

    public void setMateriaPrima(MateriaPrimaDto materiaPrima) {
        this.materiaPrima = materiaPrima;
    }
}
