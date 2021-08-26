package com.deveteris.cucina.response;

import com.deveteris.cucina.dto.PiattoMenuGiornoDto;

import java.util.Set;

public class PersistMenuGiornoResponse {
    private Set<String> piattiNonAggiunti;
    private Set<PiattoMenuGiornoDto> piattoMenuGiornoDto;

    public Set<String> getPiattiNonAggiunti() {
        return piattiNonAggiunti;
    }

    public void setPiattiNonAggiunti(Set<String> piattiNonAggiunti) {
        this.piattiNonAggiunti = piattiNonAggiunti;
    }

    public Set<PiattoMenuGiornoDto> getMenuGiornoDto() {
        return piattoMenuGiornoDto;
    }

    public void setMenuGiornoDto(Set<PiattoMenuGiornoDto> piattoMenuGiornoDto) {
        this.piattoMenuGiornoDto = piattoMenuGiornoDto;
    }
}
