package ristorante.de.veteris.mapper;

import ristorante.de.veteris.dto.OrdinazioneDto;
import ristorante.de.veteris.request.OrdinazioneRequest;
import ristorante.de.veteris.model.Ordinazione;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface OrdinazioneMapper {


    @Mappings({
            @Mapping(target = "costo", ignore = true),
            @Mapping(target = "version", ignore = true),
            @Mapping(target = "updatedDate", ignore = true),
            @Mapping(target = "stato", ignore = true),
            @Mapping(target = "creationTime", ignore = true),
            @Mapping(target = "uuid", ignore = true)
    })
    Ordinazione getEntityFromRequest(OrdinazioneRequest request);

    @Mappings({
            @Mapping(target = "version", ignore = true),
            @Mapping(target = "updatedDate", ignore = true),
            @Mapping(target = "stato", ignore = true),
            @Mapping(target = "creationTime", ignore = true),
            @Mapping(target = "costo", ignore = true),
            @Mapping(target = "uuid", ignore = true)
    })
    Ordinazione updateOrdinazioneFromRequest(@MappingTarget Ordinazione ordinazione, OrdinazioneRequest ordinazioneRequest);


    OrdinazioneDto getDtoFromEntity(Ordinazione ordinazione);


}
