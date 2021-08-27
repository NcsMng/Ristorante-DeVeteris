package com.deveteris.magazzino.mapper;

import com.deveteris.magazzino.model.PrevisioneFabbisognoMp;
import com.deveteris.magazzino.repository.MateriaPrimaRepository;
import com.deveteris.magazzino.repository.PrevisioneFabbisognoMpRepository;
import com.deveteris.magazzino.util.QuantitaMeseMp;
import org.mapstruct.*;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface PrevisioneFabbisognoMpMapper {


    @Mapping(target = "qtaNonUsata", ignore = true)
    @Mapping(target = "mese", source = "quantitaMeseMp.numeroMese")
    @Mapping(target = "materiaPrima", ignore = true)
    @Mapping(target = "id", ignore = true)
    PrevisioneFabbisognoMp getEntityFromDto(QuantitaMeseMp quantitaMeseMp, String idMp);

    @AfterMapping
    default void getEntityFromDto(@MappingTarget PrevisioneFabbisognoMp previsioneFabbisognoMp, QuantitaMeseMp quantitaMeseMp, String idMp, @Context MateriaPrimaRepository repository){
        repository.findById(idMp).ifPresent(previsioneFabbisognoMp::setMateriaPrima);
    }

    @Mapping(target = "qtaNonUsata", ignore = true)
    @Mapping(target = "mese", ignore = true)
    @Mapping(target = "materiaPrima", ignore = true)
    @Mapping(target = "id", ignore = true)
    PrevisioneFabbisognoMp updateEntityFromQuantitaMeseMp(@MappingTarget PrevisioneFabbisognoMp previsioneFabbisognoMp, QuantitaMeseMp quantitaMeseMp);
}
