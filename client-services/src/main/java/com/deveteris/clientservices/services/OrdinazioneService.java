package com.deveteris.clientservices.services;

import com.deveteris.clientservices.request.OrdinazioneRequest;
import com.deveteris.clientservices.dto.OrdinazioneDto;

import java.util.List;

public interface OrdinazioneService {

    OrdinazioneDto persistOrdinazione(OrdinazioneRequest ordinazione, String uuidOrdine);
    OrdinazioneDto getOrdine(String uuid);
    Integer deleteOrdinazione(String uuidOrdine);

    OrdinazioneDto persistOrdinazione(OrdinazioneRequest ordinazione);
    OrdinazioneDto getOrdine(Integer id);
    List<OrdinazioneDto> getAllOrdini();
    boolean deleteOrdinazione(Integer id);

}
