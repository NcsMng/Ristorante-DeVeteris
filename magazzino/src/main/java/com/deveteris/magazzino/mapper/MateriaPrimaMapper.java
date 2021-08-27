package com.deveteris.magazzino.mapper;

import com.deveteris.magazzino.model.MateriaPrima;
import com.deveteris.magazzino.requests.MateriaPrimaRequest;
import dto.MateriaPrimaDto;
import org.mapstruct.*;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface MateriaPrimaMapper {

    @Mappings({
            @Mapping(target = "updatedDate", ignore = true),
            @Mapping(target = "ordiniMateriaPrima", ignore = true),
            @Mapping(target = "creationTime", ignore = true),
            @Mapping(target = "version", ignore = true)})
    MateriaPrima getEntityFromRequest(MateriaPrimaRequest request);


    @Mappings({
            @Mapping(target = "version", ignore = true),
            @Mapping(target = "updatedDate", ignore = true),
            @Mapping(target = "ordiniMateriaPrima", ignore = true),
            @Mapping(target = "creationTime", ignore = true)
    })
    MateriaPrima updateMateriaPrimaFromRequest(@MappingTarget MateriaPrima ordinazione, MateriaPrimaRequest ordinazioneRequest);

    MateriaPrimaDto getMateriaPrimaDtoFromEntity(MateriaPrima materiaPrima);
}
