package cucina.mapper;

import cucina.dto.MenuGiornoRequest;
import cucina.model.MenuGiorno;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface MenuGiornoMapper {

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "version", ignore = true),
            @Mapping(target = "updatedDate", ignore = true),
            @Mapping(target = "creationTime", ignore = true)
    })
    MenuGiorno getEntityFromRequest(MenuGiornoRequest request);

    @Mappings({
            @Mapping(target = "version", ignore = true),
            @Mapping(target = "updatedDate", ignore = true),
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "creationTime", ignore = true)})
    MenuGiorno updateMenuGiornoFromRequest(@MappingTarget MenuGiorno MenuGiorno, MenuGiornoRequest MenuGiornoRequest);

//
//    @Mapping(target = "updatedDate", source = "")
//    @Mapping(target = "version", ignore = true)
//    @Mapping(target = "id", ignore = true)
//    @Mapping(target = "creationTime", ignore = true)
//    MenuGiorno getEntityFromDto(MenuGiornoDto MenuGiornoDto);
//
//    MenuGiornoDto getDtoFromEntity(MenuGiorno MenuGiorno);
//
//    MenuGiorno updateMenuGiornoFromDto(MenuGiorno MenuGiorno, MenuGiornoDto MenuGiornoDto);

}
