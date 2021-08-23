package magazzino.mapper;

import magazzino.model.Fornitore;
import magazzino.requests.FornitoreRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface FornitoreMapper {
    @Mappings({
            @Mapping(target = "updatedDate", ignore = true),
            @Mapping(target = "creationTime", ignore = true),
            @Mapping(target = "version", ignore = true),
            @Mapping(target = "ordiniMateriaPrima", ignore = true),
    })
    Fornitore getEntityFromRequest(FornitoreRequest request);


    @Mappings({
            @Mapping(target = "version", ignore = true),
            @Mapping(target = "updatedDate", ignore = true),
            @Mapping(target = "creationTime", ignore = true),
            @Mapping(target = "ordiniMateriaPrima", ignore = true)
    })
    Fornitore updateFornitoreFromRequest(@MappingTarget Fornitore fornitore, FornitoreRequest fornitoreRequest);

}
