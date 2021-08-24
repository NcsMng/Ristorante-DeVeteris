package ristorante.de.veteris.controller;

import ristorante.de.veteris.dto.OrdinazioneDto;
import ristorante.de.veteris.request.OrdinazioneRequest;
import ristorante.de.veteris.services.OrdinazioneService;
import ristorante.de.veteris.response.DeVeterisResponse;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clienti/ordini")
public class ClientController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ClientController.class);

    private final OrdinazioneService ordinazioneService;

    public ClientController(OrdinazioneService ordinazioneService) {
        this.ordinazioneService = ordinazioneService;
    }

    @ApiOperation(value = "/clienti/ordini/persist", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, notes = "Aggiunge o modifica l'ordine")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Ordinazione salvata con successo", response = OrdinazioneDto.class),
            @ApiResponse(code = 500, message = "Errore di sistema"),
            @ApiResponse(code = 400, message = "Dati inviati incompleti o errati"),
            @ApiResponse(code = 401, message = "Utente non autorizzato"),
            @ApiResponse(code = 403, message = "L'utente non dispone delle autorizzazioni necessarie per eseguire l'operazione")
    })
    @PostMapping(path = "/persist", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DeVeterisResponse<OrdinazioneDto>> persistOrdinazioneClienti(@RequestBody OrdinazioneRequest request, @RequestParam(required = false) String uuidOrdine){
        LOGGER.debug("New request to /persist");
        OrdinazioneDto ordinazione = ordinazioneService.persistOrdinazione(request, uuidOrdine);
        DeVeterisResponse<OrdinazioneDto> response = new DeVeterisResponse<>();
        response.setBody(ordinazione);
        LOGGER.debug("Sending response from /persist");
        return ResponseEntity.ok(response);
    }

    @ApiOperation(value = "/clienti/ordini/delete/{uuid}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, notes = "Aggiunge o modifica l'ordine")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Ordinazione salvata con successo", response = OrdinazioneDto.class),
            @ApiResponse(code = 500, message = "Errore di sistema"),
            @ApiResponse(code = 400, message = "Dati inviati incompleti o errati"),
            @ApiResponse(code = 401, message = "Utente non autorizzato"),
            @ApiResponse(code = 403, message = "L'utente non dispone delle autorizzazioni necessarie per eseguire l'operazione")
    })
    @PostMapping(path = "/delete/{uuid}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DeVeterisResponse<Boolean>> deleteOrdinazioneClienti(@PathVariable("uuid") String uuidOrdine){
        LOGGER.debug("New request to /delete/");
        Boolean ordinazione = ordinazioneService.deleteOrdinazione(uuidOrdine);
        DeVeterisResponse<Boolean> response = new DeVeterisResponse<>();
        response.setBody(ordinazione);
        LOGGER.debug("Sending response from /delete");
        return ResponseEntity.ok(response);
    }

    @ApiOperation(value = "/clienti/ordini/{uuidOrdine}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, notes = "Aggiunge o modifica l'ordine")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Ordinazione salvata con successo", response = OrdinazioneDto.class),
            @ApiResponse(code = 500, message = "Errore di sistema"),
            @ApiResponse(code = 400, message = "Dati inviati incompleti o errati"),
            @ApiResponse(code = 401, message = "Utente non autorizzato"),
            @ApiResponse(code = 403, message = "L'utente non dispone delle autorizzazioni necessarie per eseguire l'operazione")
    })
    @GetMapping(path = "/{uuidOrdine}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DeVeterisResponse<Boolean>> getOrdine(@PathVariable("uuidOrdine") String uuidOrdine){
        LOGGER.debug("New request to /delete");
        Boolean ordinazione = ordinazioneService.deleteOrdinazione(uuidOrdine);
        DeVeterisResponse<Boolean> response = new DeVeterisResponse<>();
        response.setBody(ordinazione);
        LOGGER.debug("Sending response from /delete");
        return ResponseEntity.ok(response);
    }

}
