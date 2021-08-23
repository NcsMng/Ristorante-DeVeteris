package cucina.services.impl;

import cucina.dto.MenuGiornoRequest;
import cucina.mapper.MenuGiornoMapper;
import cucina.model.MenuGiorno;
import cucina.repository.MenuGiornoRepository;
import cucina.services.MenuGiornoService;
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
