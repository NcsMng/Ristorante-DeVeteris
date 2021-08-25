package com.deveteris.cucina.services.impl;

import com.deveteris.cucina.mapper.MenuGiornoMapper;
import com.deveteris.cucina.model.Pietanza;
import com.deveteris.cucina.repository.MenuGiornoRepository;
import com.deveteris.cucina.repository.PietanzaRepository;
import com.deveteris.cucina.request.MenuGiornoRequest;
import com.deveteris.cucina.model.MenuGiorno;
import com.deveteris.cucina.response.PersistMenuGiornoResponse;
import com.deveteris.cucina.services.MenuGiornoService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class MenuGiornoServiceImpl implements MenuGiornoService {
    private final MenuGiornoRepository menuGiornoRepository;
    private final MenuGiornoMapper menuGiornoMapper;
    private final PietanzaRepository pietanzaRepository;

    public MenuGiornoServiceImpl(MenuGiornoRepository menuGiornoRepository, MenuGiornoMapper menuGiornoMapper, PietanzaRepository pietanzaRepository) {
        this.menuGiornoRepository = menuGiornoRepository;
        this.menuGiornoMapper = menuGiornoMapper;
        this.pietanzaRepository = pietanzaRepository;
    }

    @Override
    @Transactional
    public PersistMenuGiornoResponse persistMenu(MenuGiornoRequest menuGiornoRequest) {
        Set<String> pietanzeNonAggiunte = new HashSet<>();

        deleteMenu();

        Set<Pietanza> piattiMenu = menuGiornoRequest
                .getPietanze()
                .stream()
                .map(idPietanza -> {
                    Optional<Pietanza> optionalPietanza = pietanzaRepository.findById(idPietanza);
                    if (!optionalPietanza.isPresent()) {
                        pietanzeNonAggiunte.add(idPietanza);
                    }
                    return optionalPietanza;
                })
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toSet());

        PersistMenuGiornoResponse response = new PersistMenuGiornoResponse();
        MenuGiorno menuGiorno = new MenuGiorno();
        menuGiorno.setPietanze(piattiMenu);

        response.setMenuGiornoDto(
                menuGiornoMapper.getDtoFromEntity
                        (menuGiornoRepository.save(menuGiorno)));
        response.setPiattiNonAggiunti(pietanzeNonAggiunte);

        return response;
    }

    @Override
    public void deleteMenu() {
        menuGiornoRepository.deleteAll();
    }
}
