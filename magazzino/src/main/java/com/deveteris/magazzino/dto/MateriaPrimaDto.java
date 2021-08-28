package com.deveteris.magazzino.dto;

public class MateriaPrimaDto {
    private String id;
    private String nome;
    private Double quantita;
    private String descrizione;

    public MateriaPrimaDto(String id, String nome, Double quantita, String descrizione) {
        this.id = id;
        this.nome = nome;
        this.quantita = quantita;
        this.descrizione = descrizione;
    }
    public MateriaPrimaDto(){}

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
}