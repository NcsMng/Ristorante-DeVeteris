package ristorante.de.veteris.services;

import ristorante.de.veteris.dto.OrdinazioneDto;
import ristorante.de.veteris.request.OrdinazioneRequest;

import java.util.List;

public interface OrdinazioneService {

    OrdinazioneDto persistOrdinazione(OrdinazioneRequest ordinazione, String uuidOrdine);
    OrdinazioneDto getOrdine(String uuid);
    boolean deleteOrdinazione(String uuidOrdine);

    OrdinazioneDto persistOrdinazione(OrdinazioneRequest ordinazione);
    OrdinazioneDto getOrdine(Integer id);
    List<OrdinazioneDto> getAllOrdini();
    boolean deleteOrdinazione(Integer id);

}
