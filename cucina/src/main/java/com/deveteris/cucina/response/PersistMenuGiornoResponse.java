package com.deveteris.cucina.response;

import com.deveteris.cucina.dto.MenuGiornoDto;

import java.util.Set;

public class PersistMenuGiornoResponse {
    private Set<String> piattiNonAggiunti;
    private MenuGiornoDto menuGiornoDto;

    public Set<String> getPiattiNonAggiunti() {
        return piattiNonAggiunti;
    }

    public void setPiattiNonAggiunti(Set<String> piattiNonAggiunti) {
        this.piattiNonAggiunti = piattiNonAggiunti;
    }

    public MenuGiornoDto getMenuGiornoDto() {
        return menuGiornoDto;
    }

    public void setMenuGiornoDto(MenuGiornoDto menuGiornoDto) {
        this.menuGiornoDto = menuGiornoDto;
    }
}
