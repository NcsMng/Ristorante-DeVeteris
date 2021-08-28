package com.deveteris.magazzino.mapper;

import com.deveteris.magazzino.dto.PrevisioneFabbisognoMpDto;
import com.deveteris.magazzino.model.PrevisioneFabbisognoMp;
import com.deveteris.magazzino.repository.MateriaPrimaRepository;
import com.deveteris.magazzino.util.QuantitaMeseMp;
import org.mapstruct.*;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface PrevisioneFabbisognoMpMapper {


    @Mapping(target = "qtaNonUsata", ignore = true)
    @Mapping(target = "mese", source = "quantitaMeseMp.numeroMese")
    @Mapping(target = "materiaPrima", ignore = true)
    @Mapping(target = "id", ignore = true)
    PrevisioneFabbisognoMp getEntityFromDto(QuantitaMeseMp quantitaMeseMp, String idMp, @Context MateriaPrimaRepository repository);

    @AfterMapping
    default void getEntityFromDto(@MappingTarget PrevisioneFabbisognoMp previsioneFabbisognoMp, String idMp, @Context MateriaPrimaRepository repository){
        repository.findById(idMp).ifPresent(previsioneFabbisognoMp::setMateriaPrima);
    }

    @Mapping(target = "materiaPrima", ignore = true)
    PrevisioneFabbisognoMpDto getDtoFromEntity(PrevisioneFabbisognoMp previsioneFabbisognoMp,  @Context MateriaPrimaMapper mapper);

    @AfterMapping
    default void getDtoFromEntity(@MappingTarget PrevisioneFabbisognoMpDto dto, PrevisioneFabbisognoMp previsioneFabbisognoMp, @Context MateriaPrimaMapper mapper){
        dto.setMateriaPrima(mapper.getMateriaPrimaDtoFromEntity(previsioneFabbisognoMp.getMateriaPrima()));
    }

}
