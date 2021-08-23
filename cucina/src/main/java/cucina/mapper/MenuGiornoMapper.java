package cucina.mapper;

import cucina.dto.MenuGiornoDto;
import cucina.dto.MenuGiornoRequest;
import cucina.model.MenuGiorno;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MenuGiornoMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "version", ignore = true)
    @Mapping(target = "updatedDate", ignore = true)
    @Mapping(target = "creationTime", ignore = true)
    MenuGiorno getEntityFromRequest(MenuGiornoRequest request);

    MenuGiorno updateMenuGiornoFromRequest(MenuGiorno MenuGiorno, MenuGiornoRequest MenuGiornoRequest);


    @Mapping(target = "updatedDate", source = "")
    @Mapping(target = "version", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "creationTime", ignore = true)
    MenuGiorno getEntityFromDto(MenuGiornoDto MenuGiornoDto);

    MenuGiornoDto getDtoFromEntity(MenuGiorno MenuGiorno);

    MenuGiorno updateMenuGiornoFromDto(MenuGiorno MenuGiorno, MenuGiornoDto MenuGiornoDto);

}
