package com.deveteris.cucina.services;

import com.deveteris.cucina.request.MenuGiornoRequest;
import com.deveteris.cucina.model.MenuGiorno;
import com.deveteris.cucina.response.PersistMenuGiornoResponse;

public interface MenuGiornoService {

    PersistMenuGiornoResponse persistMenu(MenuGiornoRequest menuGiornoRequest);

    void deleteMenu();
}
