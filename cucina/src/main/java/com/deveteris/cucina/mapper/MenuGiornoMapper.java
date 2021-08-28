package com.deveteris.cucina.mapper;

import com.deveteris.cucina.dto.PiattoMenuGiornoDto;
import com.deveteris.cucina.exception.PietanzaNonTrovataException;
import com.deveteris.cucina.model.PiattoMenuGiorno;
import com.deveteris.cucina.repository.PietanzaRepository;
import com.deveteris.cucina.request.MenuGiornoRequest;
import org.mapstruct.*;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface MenuGiornoMapper {

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "version", ignore = true),
            @Mapping(target = "updatedDate", ignore = true),
            @Mapping(target = "creationTime", ignore = true),
            @Mapping(target = "pietanza", ignore = true)
    })
    PiattoMenuGiorno getEntityFromRequest(MenuGiornoRequest request, @Context PietanzaRepository pietanzaRepository);

    @AfterMapping
    default void getEntityFromRequest(@MappingTarget PiattoMenuGiorno piattoMenuGiorno, MenuGiornoRequest menuGiornoRequest, @Context PietanzaRepository pietanzaRepository) {
        piattoMenuGiorno
                .setPietanza(pietanzaRepository.findById(menuGiornoRequest.getPietanza())
                        .orElseThrow(() -> new PietanzaNonTrovataException("pietanza con id {} non trovata", menuGiornoRequest.getPietanza())));
    }

    @Mappings({
            @Mapping(target = "version", ignore = true),
            @Mapping(target = "updatedDate", ignore = true),
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "creationTime", ignore = true),
            @Mapping(target = "pietanza", ignore = true)
    })
    PiattoMenuGiorno updateMenuGiornoFromRequest(@MappingTarget PiattoMenuGiorno piattoMenuGiorno, MenuGiornoRequest menuGiornoRequest, @Context PietanzaRepository pietanzaRepository);

    @AfterMapping
    default void updateMenuGiornoFrom(@MappingTarget PiattoMenuGiorno piattoMenuGiorno, MenuGiornoRequest menuGiornoRequest, @Context PietanzaRepository pietanzaRepository) {
        piattoMenuGiorno.setPietanza(pietanzaRepository
                .findById(menuGiornoRequest
                        .getPietanza())
                .orElseThrow(() -> new PietanzaNonTrovataException("pietanza con id {} non trovata", menuGiornoRequest.getPietanza())));
    }


    @Mappings({
            @Mapping(target = "pietanzaDto", ignore = true)
    })
    PiattoMenuGiornoDto getDtoFromEntity(PiattoMenuGiorno piattoMenuGiorno, @Context PietanzaMapper pietanzaMapper);

    @AfterMapping
    default void getDtoFromEntity(@MappingTarget PiattoMenuGiornoDto piattoMenuGiornoDto, PiattoMenuGiorno piattoMenuGiorno, @Context PietanzaMapper pietanzaMapper) {
        piattoMenuGiornoDto.setPietanzaDto(pietanzaMapper.getPietanzaDtoFromEntity(piattoMenuGiorno.getPietanza()));
    }

}
