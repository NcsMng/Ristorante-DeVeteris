package ristorante.de.veteris.services;

import ristorante.de.veteris.dto.MenuGiornoRequest;
import ristorante.de.veteris.model.MenuGiorno;

public interface MenuGiornoService {

    MenuGiorno persistMenu(MenuGiornoRequest menuGiornoRequest);

    void deleteMenu();
}
