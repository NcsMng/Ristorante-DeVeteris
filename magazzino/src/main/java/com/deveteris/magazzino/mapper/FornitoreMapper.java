package com.deveteris.magazzino.mapper;

import com.deveteris.magazzino.model.Fornitore;
import com.deveteris.magazzino.requests.FornitoreRequest;
import org.mapstruct.*;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
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
