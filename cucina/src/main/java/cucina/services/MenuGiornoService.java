package cucina.services;

import cucina.dto.MenuGiornoRequest;
import cucina.model.MenuGiorno;

public interface MenuGiornoService {

    MenuGiorno persistMenu(MenuGiornoRequest menuGiornoRequest);

    void deleteMenu();
}
