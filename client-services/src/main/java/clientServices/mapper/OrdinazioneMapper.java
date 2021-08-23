package clientServices.mapper;

import clientServices.dto.OrdinazioneRequest;
import notificationsmanager.model.Ordinazione;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface OrdinazioneMapper {

//    @Mapping(target = "updatedDate", source = "")
//    @Mapping(target = "version", ignore = true)
//    @Mapping(target = "stato", ignore = true)
//    @Mapping(target = "id", ignore = true)
//    @Mapping(target = "creationTime", ignore = true)
//    Ordinazione getEntityFromDto(OrdinazioneDto ordinazioneDto);

    @Mappings({
            @Mapping(target = "costo", ignore = true),
            @Mapping(target = "version", ignore = true),
            @Mapping(target = "updatedDate", ignore = true),
            @Mapping(target = "stato", ignore = true),
            @Mapping(target = "creationTime", ignore = true)
    })
    Ordinazione getEntityFromRequest(OrdinazioneRequest request);

    @Mappings({
            @Mapping(target = "version", ignore = true),
            @Mapping(target = "updatedDate", ignore = true),
            @Mapping(target = "stato", ignore = true),
            @Mapping(target = "creationTime", ignore = true),
            @Mapping(target = "costo", ignore = true)
    })
    Ordinazione updateOrdinazioneFromRequest(@MappingTarget Ordinazione ordinazione, OrdinazioneRequest ordinazioneRequest);

//    OrdinazioneDto getDtoFromEntity(Ordinazione ordinazione);
//
//    Ordinazione updateOrdinazioneFromDto(Ordinazione ordinazione, OrdinazioneDto ordinazioneDto);

}
