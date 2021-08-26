package com.deveteris.cucina.dto;

public class PiattoMenuGiornoDto {
    private Integer id;
    private PietanzaDto pietanzaDto;

    public PiattoMenuGiornoDto(PietanzaDto pietanzaDto) {
        this.pietanzaDto = pietanzaDto;
    }

    public PiattoMenuGiornoDto(){}
    public PietanzaDto getPietanzaDto() {
        return pietanzaDto;
    }

    public void setPietanzaDto(PietanzaDto pietanzaDto) {
        this.pietanzaDto = pietanzaDto;
    }

}
