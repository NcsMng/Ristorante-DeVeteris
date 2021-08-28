package com.deveteris.magazzino.mapper;

import com.deveteris.magazzino.dto.MateriaPrimaDto;
import com.deveteris.magazzino.dto.OrdineDto;
import com.deveteris.magazzino.model.Ordine;
import com.deveteris.magazzino.model.OrdineMateriaPrima;
import com.deveteris.magazzino.repository.OrdineMateriaPrimaRepository;
import com.deveteris.magazzino.requests.OrdineRequest;
import org.mapstruct.*;

import java.util.Set;
import java.util.stream.Collectors;

    @Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface OrdiniMapper {

    @Mapping(target = "version", ignore = true)
    @Mapping(target = "updatedDate", ignore = true)
    @Mapping(target = "statoOrdine", ignore = true)
    @Mapping(target = "ordiniMateriaPrima", ignore = true)
    @Mapping(target = "creationTime", ignore = true)
    Ordine getEntityFromRequest(OrdineRequest request);



    @Mapping(target = "version", ignore = true)
    @Mapping(target = "updatedDate", ignore = true)
    @Mapping(target = "statoOrdine", ignore = true)
    @Mapping(target = "ordiniMateriaPrima", ignore = true)
    @Mapping(target = "creationTime", ignore = true)
    Ordine updateOrdineFromRequest(@MappingTarget Ordine ordinazione, OrdineRequest ordinazioneRequest);

    @Mapping(target = "ordiniMateriaPrima", ignore = true)
    OrdineDto getOrdineDtoFromEntity(Ordine ordine, @Context OrdineMateriaPrimaRepository ordineMateriaPrimaRepository, @Context MateriaPrimaMapper mapper);

    @AfterMapping
    default void getOrdineDtoFromEntityAM(@MappingTarget OrdineDto ordineDto, Ordine ordine, @Context OrdineMateriaPrimaRepository ordineMateriaPrimaRepository, @Context MateriaPrimaMapper mapper){
        Set<MateriaPrimaDto> materiePrimeDto = ordineMateriaPrimaRepository
                .findAllByOrdine_Id(ordine.getId())
                .stream()
                .map(OrdineMateriaPrima::getMateriaPrima)
                .map(mapper::getMateriaPrimaDtoFromEntity)
                .collect(Collectors.toSet());
        ordineDto.setOrdiniMateriaPrima(materiePrimeDto);
    }

}
