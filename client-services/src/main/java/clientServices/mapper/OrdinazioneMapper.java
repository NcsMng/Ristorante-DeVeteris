package clientServices.mapper;

import clientServices.dto.OrdinazioneDto;
import notificationsmanager.model.Ordinazione;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface OrdinazioneMapper {

    @Mapping(target = "updatedDate", source = "")
    @Mapping(target = "version", ignore = true)
    @Mapping(target = "stato", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "creationTime", ignore = true)
    Ordinazione getEntityFromDto(OrdinazioneDto ordinazioneDto);

    OrdinazioneDto getDtoFromEntity(Ordinazione ordinazione);

    Ordinazione updateOrdinazioneFromDto(Ordinazione ordinazione, OrdinazioneDto ordinazioneDto);
}
