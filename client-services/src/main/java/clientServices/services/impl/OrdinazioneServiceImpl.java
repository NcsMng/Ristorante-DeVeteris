package clientServices.services.impl;

import clientServices.dto.OrdinazioneDto;
import clientServices.exception.OrdinazioneNonTrovataException;
import clientServices.mapper.OrdinazioneMapper;
import clientServices.services.OrdinazioneService;
import lombok.extern.slf4j.Slf4j;
import notificationsmanager.model.Ordinazione;
import notificationsmanager.repositories.OrdinazioniRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class OrdinazioneServiceImpl implements OrdinazioneService {

    private final OrdinazioneMapper ordinazioneMapper;
    private final OrdinazioniRepository ordinazioniRepository;

    public OrdinazioneServiceImpl(OrdinazioneMapper ordinazioneMapper, OrdinazioniRepository ordinazioniRepository) {
        this.ordinazioneMapper = ordinazioneMapper;
        this.ordinazioniRepository = ordinazioniRepository;
    }

    //Save or Update Entity via DTO
    @Override
    public Ordinazione persistOrdinazione(OrdinazioneDto ordinazioneDto) {
        return Optional.ofNullable(ordinazioneDto.getId())
                .map(ordinazioneId ->
                {
                    Ordinazione ordinazione = ordinazioniRepository.findById(ordinazioneId)
                            .orElseThrow(() -> new OrdinazioneNonTrovataException("Ordinazione con id {} non trovata", ordinazioneId));
                    return ordinazioneMapper.updateOrdinazioneFromDto(ordinazione, ordinazioneDto);
                })
                .orElseGet(() -> ordinazioniRepository
                        .save(ordinazioneMapper.getEntityFromDto(ordinazioneDto)));
    }

}
