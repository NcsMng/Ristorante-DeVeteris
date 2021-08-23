package clientServices.services;

import clientServices.dto.OrdinazioneRequest;
import notificationsmanager.model.Ordinazione;

public interface OrdinazioneService {
    Ordinazione persistOrdinazione(OrdinazioneRequest ordinazione);

    Ordinazione persistOrdinazione(OrdinazioneRequest ordinazione, String uuidOrdine);

    boolean deleteOrdinazione(String uuidOrdine);

    boolean deleteOrdinazione(Integer id);
}
