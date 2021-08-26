package com.deveteris.cucina.services;

import com.deveteris.cucina.dto.PietanzaDto;
import com.deveteris.cucina.request.MenuGiornoRequest;
import com.deveteris.cucina.response.PersistMenuGiornoResponse;

import java.util.Set;

public interface MenuGiornoService {

    PersistMenuGiornoResponse persistMenu(Set<MenuGiornoRequest> menuGiornoRequest);

    Boolean deleteMenu();

    Boolean deletePiattoFromMenu(String idString);

    PietanzaDto persistPietanza(String idString);
}
