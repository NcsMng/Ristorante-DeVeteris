package cucina.mapper;

import cucina.dto.PietanzaRequest;
import cucina.model.Pietanza;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PietanzaMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "version", ignore = true)
    @Mapping(target = "updatedDate", ignore = true)
    @Mapping(target = "creationTime", ignore = true)
    Pietanza getEntityFromRequest(PietanzaRequest request);

    Pietanza updatePietanzaFromRequest(Pietanza Pietanza, PietanzaRequest PietanzaRequest);
}
