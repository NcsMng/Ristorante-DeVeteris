package magazzino.mapper;

import magazzino.model.MateriaPrima;
import magazzino.requests.MateriaPrimaRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
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

}