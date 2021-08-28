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
            @Mapping(target = "piattiOrdinazione", ignore = true)
    })
    Ordinazione getEntityFromRequest(OrdinazioneRequest request, @Context PiattiOrdinazioneRepository repository, @Context PiattoOrdinazioneMapper piattoOrdinazioneMapper);

    @AfterMapping
    default void getEntityFromRequest(@MappingTarget Ordinazione ordinazione, OrdinazioneRequest ordinazioneRequest, @Context PiattiOrdinazioneRepository repository, @Context PiattoOrdinazioneMapper piattoOrdinazioneMapper) {
        ordinazioneRequest.getPiattiOrdinazione().forEach(piattoOrdinazioneRequest -> {
            PiattoOrdinazione piattoOrdinazione = repository
                    .findByCodicePiatto(piattoOrdinazioneRequest.getCodicePiatto())
                    .orElseGet(() -> piattoOrdinazioneMapper.getEntityFromRequest(piattoOrdinazioneRequest));
            piattoOrdinazione.setOrdinazione(ordinazione);
            ordinazione.getPiattiOrdinazione().add(piattoOrdinazione);
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
    Ordinazione updateOrdinazioneFromRequest(@MappingTarget Ordinazione ordinazione, OrdinazioneRequest ordinazioneRequest, @Context PiattiOrdinazioneRepository repository, @Context PiattoOrdinazioneMapper mapper);

    @AfterMapping
    default void updateOrdinazioneFromRequestAfter(@MappingTarget Ordinazione ordinazione, OrdinazioneRequest ordinazioneRequest, @Context PiattiOrdinazioneRepository repository, @Context PiattoOrdinazioneMapper mapper) {
        ordinazioneRequest.getPiattiOrdinazione().forEach(piattoOrdinazioneRequest -> {
            Optional<PiattoOrdinazione> optionalPiattoOrdinazione = ordinazione.getPiattiOrdinazione()
                    .stream()
                    .filter(piattoOrdinazione -> piattoOrdinazione.getCodicePiatto().equals(piattoOrdinazioneRequest.getCodicePiatto()))
                    .findAny();
            if (optionalPiattoOrdinazione.isPresent()) {
                PiattoOrdinazione piattoOrdinazione = optionalPiattoOrdinazione.get();
                piattoOrdinazione.setQuantita(piattoOrdinazioneRequest.getQuantita());
                piattoOrdinazione.setNote(piattoOrdinazioneRequest.getNote());
            } else {
                ordinazione.getPiattiOrdinazione().add(mapper.getEntityFromRequest(piattoOrdinazioneRequest));
            }
        });
    }

    OrdinazioneDto getDtoFromEntity(Ordinazione ordinazione, @Context PiattoOrdinazioneMapper piattoOrdinazioneMapper);

    @AfterMapping
    default void mapPiattiOrdinazione(Ordinazione ordinazione, @MappingTarget OrdinazioneDto ordinazioneDto, @Context PiattoOrdinazioneMapper mapper) {
        ordinazioneDto.setPiattiOrdinazione(ordinazione.getPiattiOrdinazione()
                .stream()
                .map(mapper::getPiattoDtoFromEntity)
                .collect(Collectors.toSet()));
    }
}
