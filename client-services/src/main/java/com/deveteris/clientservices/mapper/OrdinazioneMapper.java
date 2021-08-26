package com.deveteris.clientservices.mapper;

import com.deveteris.clientservices.request.OrdinazioneRequest;
import org.mapstruct.*;
import com.deveteris.clientservices.dto.OrdinazioneDto;
import com.deveteris.clientservices.dto.PiattoOrdinazioneDto;
import com.deveteris.notificationsmanager.model.PiattoOrdinazione;
import com.deveteris.notificationsmanager.model.Ordinazione;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring",nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public abstract class OrdinazioneMapper {


    @Mappings({
            @Mapping(target = "costo", ignore = true),
            @Mapping(target = "version", ignore = true),
            @Mapping(target = "updatedDate", ignore = true),
            @Mapping(target = "stato", ignore = true),
            @Mapping(target = "creationTime", ignore = true),
            @Mapping(target = "uuid", ignore = true)
    })
    public abstract Ordinazione getEntityFromRequest(OrdinazioneRequest request);

    @Mappings({
            @Mapping(target = "version", ignore = true),
            @Mapping(target = "updatedDate", ignore = true),
            @Mapping(target = "stato", ignore = true),
            @Mapping(target = "creationTime", ignore = true),
            @Mapping(target = "costo", ignore = true),
            @Mapping(target = "uuid", ignore = true)
    })
    public abstract Ordinazione updateOrdinazioneFromRequest(@MappingTarget Ordinazione ordinazione, OrdinazioneRequest ordinazioneRequest);

    public abstract PiattoOrdinazioneDto getPiattoDtoFromEntity(PiattoOrdinazione piattoOrdinazione);

    @Mappings({
            @Mapping(target = "piattiOrdinazione", qualifiedByName = "mapPiattiOrdinazione")
    })
    public abstract  OrdinazioneDto getDtoFromEntity(Ordinazione ordinazione);

    @Named("mapPiattiOrdinazione")
    public Set<PiattoOrdinazioneDto> mapPiattiOrdinazione(Set<PiattoOrdinazione> piattoOrdinazioni){
        return piattoOrdinazioni
                .stream()
                .map(this::getPiattoDtoFromEntity)
                .collect(Collectors.toSet());
    }
}
