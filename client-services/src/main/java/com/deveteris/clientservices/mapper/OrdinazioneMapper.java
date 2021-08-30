package com.deveteris.clientservices.mapper;

import com.deveteris.clientservices.dto.OrdinazioneDto;
import com.deveteris.clientservices.request.OrdinazioneRequest;
import com.deveteris.notificationsmanager.model.Ordinazione;
import com.deveteris.notificationsmanager.model.PiattoOrdinazione;
import com.deveteris.notificationsmanager.repository.PiattiOrdinazioneRepository;
import org.mapstruct.*;

import java.util.Optional;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface OrdinazioneMapper {

    @Mappings({
            @Mapping(target = "costo", ignore = true),
            @Mapping(target = "version", ignore = true),
            @Mapping(target = "updatedDate", ignore = true),
            @Mapping(target = "stato", ignore = true),
            @Mapping(target = "creationTime", ignore = true),
            @Mapping(target = "uuid", ignore = true),
            @Mapping(target = "piattiOrdinazione", ignore = true),
            @Mapping(target = "id", ignore = true)
    })
    @BeanMapping(qualifiedByName = "getEntityFromRequest")
    Ordinazione getEntityFromRequest(OrdinazioneRequest request, @Context PiattiOrdinazioneRepository repository, @Context PiattoOrdinazioneMapper piattoOrdinazioneMapper);

    @AfterMapping
    @Named("getEntityFromRequest")
    default void getEntityFromRequestAfter(@MappingTarget Ordinazione ordinazione, OrdinazioneRequest ordinazioneRequest, @Context PiattiOrdinazioneRepository repository, @Context PiattoOrdinazioneMapper piattoOrdinazioneMapper) {
        ordinazioneRequest.getPiattiOrdinazione().forEach(piattoOrdinazioneRequest -> {
            PiattoOrdinazione entityFromRequest = piattoOrdinazioneMapper.getEntityFromRequest(piattoOrdinazioneRequest);
            entityFromRequest.setOrdinazione(ordinazione);
            ordinazione.getPiattiOrdinazione().add(entityFromRequest);
        });
    }

    @Mappings({
            @Mapping(target = "version", ignore = true),
            @Mapping(target = "updatedDate", ignore = true),
            @Mapping(target = "stato", ignore = true),
            @Mapping(target = "creationTime", ignore = true),
            @Mapping(target = "costo", ignore = true),
            @Mapping(target = "uuid", ignore = true),
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "piattiOrdinazione", ignore = true)
    })
    @BeanMapping(qualifiedByName = "updateOrdinazioneFromRequest")
    Ordinazione updateOrdinazioneFromRequest(@MappingTarget Ordinazione ordinazione, OrdinazioneRequest ordinazioneRequest, @Context PiattiOrdinazioneRepository repository, @Context PiattoOrdinazioneMapper mapper);

    @AfterMapping
    @Named("updateOrdinazioneFromRequest")
    default void updateOrdinazioneFromRequestAfter(@MappingTarget Ordinazione ordinazione, OrdinazioneRequest ordinazioneRequest, @Context PiattiOrdinazioneRepository repository, @Context PiattoOrdinazioneMapper mapper) {
        ordinazioneRequest.getPiattiOrdinazione()
                .forEach(piattoOrdinazioneRequest -> {
                    Optional<PiattoOrdinazione> optionalPiattoOrdinazione = ordinazione.getPiattiOrdinazione()
                            .stream()
                            .filter(piattoOrdinazione -> piattoOrdinazione.getCodicePiatto().equals(piattoOrdinazioneRequest.getCodicePiatto()))
                            .findAny();
                    if (optionalPiattoOrdinazione.isPresent()) {
                        PiattoOrdinazione piattoOrdinazione = optionalPiattoOrdinazione.get();
                        piattoOrdinazione.setQuantita(piattoOrdinazioneRequest.getQuantita());
                        piattoOrdinazione.setNote(piattoOrdinazioneRequest.getNote());
                        repository.save(piattoOrdinazione);
                    } else {
                        PiattoOrdinazione entityFromRequest = mapper.getEntityFromRequest(piattoOrdinazioneRequest);
                        ordinazione.getPiattiOrdinazione().add(entityFromRequest);
                        entityFromRequest.setOrdinazione(ordinazione);
                        repository.save(entityFromRequest);
                    }
                });

    }

    @BeanMapping(qualifiedByName = "getDtoFromEntity")
    OrdinazioneDto getDtoFromEntity(Ordinazione ordinazione, @Context PiattoOrdinazioneMapper piattoOrdinazioneMapper);

    @AfterMapping
    @Named("getDtoFromEntity")
    default void getDtoFromEntityAfter(Ordinazione ordinazione, @MappingTarget OrdinazioneDto ordinazioneDto, @Context PiattoOrdinazioneMapper mapper) {
        ordinazioneDto.setPiattiOrdinazione(ordinazione.getPiattiOrdinazione()
                .stream()
                .map(mapper::getPiattoDtoFromEntity)
                .collect(Collectors.toSet()));
    }
}
