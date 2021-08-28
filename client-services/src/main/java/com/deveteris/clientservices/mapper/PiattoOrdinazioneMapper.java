package com.deveteris.clientservices.mapper;

import com.deveteris.clientservices.dto.PiattoOrdinazioneDto;
import com.deveteris.clientservices.request.PiattoOrdinazioneRequest;
import com.deveteris.notificationsmanager.model.PiattoOrdinazione;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface PiattoOrdinazioneMapper {

    @Mapping(target = "version", ignore = true)
    @Mapping(target = "updatedDate", ignore = true)
    @Mapping(target = "ordinazione", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "creationTime", ignore = true)
    PiattoOrdinazione getEntityFromRequest(PiattoOrdinazioneRequest request);

    PiattoOrdinazioneDto getPiattoDtoFromEntity(PiattoOrdinazione piattoOrdinazione);

}
