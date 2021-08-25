package com.deveteris.cucina.mapper;

import com.deveteris.cucina.dto.MenuGiornoDto;
import com.deveteris.cucina.dto.PietanzaDto;
import com.deveteris.cucina.model.Pietanza;
import com.deveteris.cucina.request.MenuGiornoRequest;
import com.deveteris.cucina.model.MenuGiorno;
import com.deveteris.notificationsmanager.model.PiattoOrdinazione;
import org.mapstruct.*;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public abstract class MenuGiornoMapper {

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "version", ignore = true),
            @Mapping(target = "updatedDate", ignore = true),
            @Mapping(target = "creationTime", ignore = true)
    })
    public abstract MenuGiorno getEntityFromRequest(MenuGiornoRequest request);

    @Mappings({
            @Mapping(target = "version", ignore = true),
            @Mapping(target = "updatedDate", ignore = true),
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "creationTime", ignore = true)})
    public abstract MenuGiorno updateMenuGiornoFromRequest(@MappingTarget MenuGiorno MenuGiorno, MenuGiornoRequest MenuGiornoRequest);


    public abstract PietanzaDto getPietanzaDtoFromEntity(Pietanza pietanza);

    @Mappings({
            @Mapping(target = "pietanzaDto", qualifiedByName = "mapPiattiMenu")
    })
    public abstract MenuGiornoDto getDtoFromEntity(MenuGiorno MenuGiorno);

    @Named("mapPiattiMenu")
    public Set<PietanzaDto> mapPiattiOrdinazione(Set<Pietanza> pietanzaOrdinazioni){
        return pietanzaOrdinazioni
                .stream()
                .map(this::getPietanzaDtoFromEntity)
                .collect(Collectors.toSet());
    }
}
