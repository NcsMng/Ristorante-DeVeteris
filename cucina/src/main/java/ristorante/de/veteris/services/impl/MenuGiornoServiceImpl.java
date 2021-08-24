package ristorante.de.veteris.services.impl;

import ristorante.de.veteris.dto.MenuGiornoRequest;
import ristorante.de.veteris.mapper.MenuGiornoMapper;
import ristorante.de.veteris.model.MenuGiorno;
import ristorante.de.veteris.repository.MenuGiornoRepository;
import ristorante.de.veteris.services.MenuGiornoService;
import org.springframework.stereotype.Service;

@Service
public class MenuGiornoServiceImpl implements MenuGiornoService {
    private final MenuGiornoRepository menuGiornoRepository;
    private final MenuGiornoMapper menuGiornoMapper;

    public MenuGiornoServiceImpl(MenuGiornoRepository menuGiornoRepository, MenuGiornoMapper menuGiornoMapper) {
        this.menuGiornoRepository = menuGiornoRepository;
        this.menuGiornoMapper = menuGiornoMapper;
    }

    @Override
    public MenuGiorno persistMenu(MenuGiornoRequest menuGiornoRequest) {
        deleteMenu();
        return menuGiornoRepository.save(menuGiornoMapper.getEntityFromRequest(menuGiornoRequest));
    }

    @Override
    public void deleteMenu() {
        menuGiornoRepository.deleteAll();
    }
}
