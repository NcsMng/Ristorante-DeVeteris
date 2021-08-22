package clientServices.services;

import clientServices.dto.OrdinazioneDto;
import notificationsmanager.model.Ordinazione;

public interface OrdinazioneService {
    Ordinazione persistOrdinazione(OrdinazioneDto ordinazioneDto);
}
