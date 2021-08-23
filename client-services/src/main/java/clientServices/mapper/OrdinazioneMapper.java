package clientServices.mapper;

import clientServices.dto.OrdinazioneRequest;
import clientServices.dto.OrdinazioneDto;
import notificationsmanager.model.Ordinazione;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrdinazioneMapper {

    @Mapping(target = "updatedDate", source = "")
    @Mapping(target = "version", ignore = true)
    @Mapping(target = "stato", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "creationTime", ignore = true)
    Ordinazione getEntityFromDto(OrdinazioneDto ordinazioneDto);

    @Mapping(target = "version", ignore = true)
    @Mapping(target = "updatedDate", ignore = true)
    @Mapping(target = "stato", ignore = true)
    @Mapping(target = "creationTime", ignore = true)
    Ordinazione getEntityFromRequest(OrdinazioneRequest request);

    OrdinazioneDto getDtoFromEntity(Ordinazione ordinazione);

    Ordinazione updateOrdinazioneFromDto(Ordinazione ordinazione, OrdinazioneDto ordinazioneDto);

    Ordinazione updateOrdinazioneFromRequest(Ordinazione ordinazione, OrdinazioneRequest ordinazioneRequest);
}
