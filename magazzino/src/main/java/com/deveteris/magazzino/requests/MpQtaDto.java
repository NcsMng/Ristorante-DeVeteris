package com.deveteris.magazzino.requests;

public class MpQtaDto {
    private String idMp;
    private Double qta;

    public MpQtaDto(String idMp, Double qta) {
        this.idMp = idMp;
        this.qta = qta;
    }

    public String getIdMp() {
        return idMp;
    }

    public void setIdMp(String idMp) {
        this.idMp = idMp;
    }

    public Double getQta() {
        return qta;
    }

    public void setQta(Double qta) {
        this.qta = qta;
    }
}
