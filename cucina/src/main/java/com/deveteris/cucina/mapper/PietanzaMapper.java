package com.deveteris.cucina.mapper;

import com.deveteris.cucina.dto.PietanzaDto;
import com.deveteris.cucina.request.PietanzaRequest;
import com.deveteris.cucina.model.Pietanza;
import org.mapstruct.*;

@Mapper(componentModel = "spring",  nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface PietanzaMapper {

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "version", ignore = true),
            @Mapping(target = "updatedDate", ignore = true),
            @Mapping(target = "creationTime", ignore = true)})
    Pietanza getEntityFromRequest(PietanzaRequest request);

    PietanzaDto getPietanzaDtoFromEntity(Pietanza pietanza);

    @Mappings({
            @Mapping(target = "version", ignore = true),
            @Mapping(target = "updatedDate", ignore = true),
            @Mapping(target = "creationTime", ignore = true)
    })
    Pietanza updatePietanzaFromRequest(@MappingTarget Pietanza Pietanza, PietanzaRequest PietanzaRequest);
}
