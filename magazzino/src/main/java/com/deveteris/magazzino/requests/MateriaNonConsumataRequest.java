package com.deveteris.magazzino.requests;

public class MateriaNonConsumataRequest {
    private String idMateriaPrima;
    private Double quantitaNonUsata;
    private Integer mese;

    public MateriaNonConsumataRequest(String idMateriaPrima, Double quantitaNonUsata,Integer mese) {
        this.idMateriaPrima = idMateriaPrima;
        this.quantitaNonUsata = quantitaNonUsata;
        this.mese = mese;
    }

    public MateriaNonConsumataRequest(){

    }

    public Integer getMese() {
        return mese;
    }

    public void setMese(Integer mese) {
        this.mese = mese;
    }

    public String getIdMateriaPrima() {
        return idMateriaPrima;
    }

    public void setIdMateriaPrima(String idMateriaPrima) {
        this.idMateriaPrima = idMateriaPrima;
    }

    public Double getQuantitaNonUsata() {
        return quantitaNonUsata;
    }

    public void setQuantitaNonUsata(Double quantitaNonUsata) {
        this.quantitaNonUsata = quantitaNonUsata;
    }
}
