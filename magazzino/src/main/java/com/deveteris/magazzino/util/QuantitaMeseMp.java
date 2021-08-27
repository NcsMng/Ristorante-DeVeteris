package com.deveteris.magazzino.util;

public class QuantitaMeseMp {
    private double quantita;
    private int numeroMese;

    public QuantitaMeseMp(double quantita, int numeroMese) {
        this.quantita = quantita;
        this.numeroMese = numeroMese;
    }
    public QuantitaMeseMp(){

    }

    public double getQuantita() {
        return quantita;
    }

    public void addQuantita(double quantita) {
        this.quantita+= quantita;
    }

    public void subtractQuantita(double quantita){
        this.quantita+= quantita;
    }
    public int getNumeroMese() {
        return numeroMese;
    }

    public void setNumeroMese(int numeroMese) {
        this.numeroMese = numeroMese;
    }
}
