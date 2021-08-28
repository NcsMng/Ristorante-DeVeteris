package com.deveteris.cucina.services;

import com.deveteris.cucina.dto.PietanzaDto;
import com.deveteris.cucina.request.MenuGiornoRequest;
import com.deveteris.cucina.response.PersistMenuGiornoResponse;

import java.util.Set;

public interface MenuGiornoService {
    /**
     * inserimento piatti dei piatti forniti per codice piatto nel menu del giorno
     * @param menuGiornoRequest, set di codice pietanze da aggiungere al menu del giorno
     * @return PersistMenuGiornoResponse, lista dei piatti ciascuno con i suoi dati e lista dei piatti non presenti
     * nel menu normale
     */
    PersistMenuGiornoResponse persistMenu(Set<MenuGiornoRequest> menuGiornoRequest);

    /**
     * cancellazione totale del menu giornaliero
     * @return Boolean per indicare l'avvenuta cancellazione
     */
    Boolean deleteMenu();

    /**
     * cancellazione pietanza fornita dal menu giornaliero
     * @param idString id piatto che si vuole cancellare dal menu
     * @return Boolean per indicare l'avvenuta cancellazione
     */
    Boolean deletePiattoFromMenu(String idString);

    /**
     * aggiunta piatto individuale al menu del giorno per codice piatto
     * @param idString id piatto da aggiungere al menu giornaliero
     * @return PietanzaDto dto pietanza che abbiamo inserito
     */
    PietanzaDto persistPietanza(String idString);
}
