package com.deveteris.cucina.mapper;

import com.deveteris.cucina.request.PietanzaRequest;
import com.deveteris.cucina.model.Pietanza;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface PietanzaMapper {

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "version", ignore = true),
            @Mapping(target = "updatedDate", ignore = true),
            @Mapping(target = "creationTime", ignore = true)})
    Pietanza getEntityFromRequest(PietanzaRequest request);

    @Mappings({
            @Mapping(target = "version", ignore = true),
            @Mapping(target = "updatedDate", ignore = true),
            @Mapping(target = "creationTime", ignore = true)
    })
    Pietanza updatePietanzaFromRequest(@MappingTarget Pietanza Pietanza, PietanzaRequest PietanzaRequest);
}
