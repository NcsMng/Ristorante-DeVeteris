package com.deveteris.cucina.dto;

import java.util.HashSet;
import java.util.Set;

public class MenuGiornoDto {
    private Set<PietanzaDto> pietanzaDto = new HashSet<>();

    public MenuGiornoDto(Set<PietanzaDto> pietanzaDto) {
        this.pietanzaDto = pietanzaDto;
    }

    public MenuGiornoDto(){}
    public Set<PietanzaDto> getPietanzaDto() {
        return pietanzaDto;
    }

    public void setPietanzaDto(Set<PietanzaDto> pietanzaDto) {
        this.pietanzaDto = pietanzaDto;
    }

}
