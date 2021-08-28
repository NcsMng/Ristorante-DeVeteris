package com.deveteris.cucina.services.impl;

import com.deveteris.cucina.dto.PiattoMenuGiornoDto;
import com.deveteris.cucina.dto.PietanzaDto;
import com.deveteris.cucina.exception.PietanzaNonTrovataException;
import com.deveteris.cucina.mapper.MenuGiornoMapper;
import com.deveteris.cucina.mapper.PietanzaMapper;
import com.deveteris.cucina.model.PiattoMenuGiorno;
import com.deveteris.cucina.model.Pietanza;
import com.deveteris.cucina.repository.MenuGiornoRepository;
import com.deveteris.cucina.repository.PietanzaRepository;
import com.deveteris.cucina.request.MenuGiornoRequest;
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
    private final PietanzaMapper pietanzaMapper;

    public MenuGiornoServiceImpl(MenuGiornoRepository menuGiornoRepository, MenuGiornoMapper menuGiornoMapper, PietanzaRepository pietanzaRepository, PietanzaMapper pietanzaMapper) {
        this.menuGiornoRepository = menuGiornoRepository;
        this.menuGiornoMapper = menuGiornoMapper;
        this.pietanzaRepository = pietanzaRepository;
        this.pietanzaMapper = pietanzaMapper;
    }

    @Override
    public PersistMenuGiornoResponse persistMenu(Set<MenuGiornoRequest> menuGiornoRequest) {
        Set<String> pietanzeNonAggiunte = new HashSet<>();

        deleteMenu();

        Set<Pietanza> piattiMenu = menuGiornoRequest
                .stream()
                .map(piattoMenuGiorno -> {
                    String idPietanza = piattoMenuGiorno.getPietanza();
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

        Set<PiattoMenuGiornoDto> menuGiornoSalvato = piattiMenu.stream().map(piattoMenu -> {
            PiattoMenuGiorno piattoMenuGiorno = new PiattoMenuGiorno();
            piattoMenuGiorno.setPietanza(piattoMenu);
            return menuGiornoMapper.getDtoFromEntity(menuGiornoRepository.save(piattoMenuGiorno),pietanzaMapper);
        }).collect(Collectors.toSet());

        response.setMenuGiornoDto(menuGiornoSalvato);
        response.setPiattiNonAggiunti(pietanzeNonAggiunte);

        return response;
    }
    @Override
    public Boolean deleteMenu() {
        menuGiornoRepository.deleteAll();
        return menuGiornoRepository.count() == 0;
    }

    @Override
    @Transactional
    public Boolean deletePiattoFromMenu(String idString) {
        return menuGiornoRepository.deleteByPietanzaId(idString) == 1;
    }

    @Override
    public PietanzaDto persistPietanza(String idString) {
        return pietanzaRepository
                .findById(idString)
                .map(pietanzaEntity -> {
                    PiattoMenuGiorno entity = new PiattoMenuGiorno();
                    entity.setPietanza(pietanzaEntity);
                    PiattoMenuGiorno savedEntity = menuGiornoRepository.save(entity);
                    return savedEntity.getPietanza();
                })
                .map(pietanzaMapper::getPietanzaDtoFromEntity)
                .orElseThrow(() -> new PietanzaNonTrovataException("Pietenza con id {} non trovata", idString));
    }
}
