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

import java.util.List;

@RestController
@RequestMapping("/staff/ordini")
public class StaffController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ClientController.class);

    private final OrdinazioneService ordinazioneService;

    public StaffController(OrdinazioneService ordinazioneService) {
        this.ordinazioneService = ordinazioneService;
    }

    @ApiOperation(value = "/staff/ordini/delete/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, notes = "Aggiunge o modifica l'ordine")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Ordinazione cancellata con successo", response = OrdinazioneDto.class),
            @ApiResponse(code = 500, message = "Errore di sistema"),
            @ApiResponse(code = 400, message = "Dati inviati incompleti o errati"),
            @ApiResponse(code = 401, message = "Utente non autorizzato"),
            @ApiResponse(code = 403, message = "L'utente non dispone delle autorizzazioni necessarie per eseguire l'operazione")
    })
    @PostMapping(path = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DeVeterisResponse<Boolean>> deleteOrdinazione(@PathVariable("id") Integer id) {
        LOGGER.debug("New request to /delete");
        Boolean ordinazione = ordinazioneService.deleteOrdinazione(id);
        DeVeterisResponse<Boolean> response = new DeVeterisResponse<>();
        response.setBody(ordinazione);
        LOGGER.debug("Sending response from /delete");
        return ResponseEntity.ok(response);
    }

    @ApiOperation(value = "/staff/ordini/persist", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, notes = "Aggiunge o modifica l'ordine")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Ordinazione salvata con successo", response = OrdinazioneDto.class),
            @ApiResponse(code = 500, message = "Errore di sistema"),
            @ApiResponse(code = 400, message = "Dati inviati incompleti o errati"),
            @ApiResponse(code = 401, message = "Utente non autorizzato"),
            @ApiResponse(code = 403, message = "L'utente non dispone delle autorizzazioni necessarie per eseguire l'operazione")
    })
    @PostMapping(path = "/persist", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DeVeterisResponse<OrdinazioneDto>> persistOrdinazione(@RequestBody OrdinazioneRequest request) {
        LOGGER.debug("New request to /persist");
        OrdinazioneDto ordinazione = ordinazioneService.persistOrdinazione(request);
        DeVeterisResponse<OrdinazioneDto> response = new DeVeterisResponse<>();
        response.setBody(ordinazione);
        LOGGER.debug("Sending response from /persist");
        return ResponseEntity.ok(response);
    }

    @ApiOperation(value = "/staff/ordini", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, notes = "Aggiunge o modifica l'ordine")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Ordinazioni recuperate con successo", response = OrdinazioneDto.class),
            @ApiResponse(code = 500, message = "Errore di sistema"),
            @ApiResponse(code = 400, message = "Dati inviati incompleti o errati"),
            @ApiResponse(code = 401, message = "Utente non autorizzato"),
            @ApiResponse(code = 403, message = "L'utente non dispone delle autorizzazioni necessarie per eseguire l'operazione")
    })
    @GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DeVeterisResponse<List<OrdinazioneDto>>> getAllOrdine() {
        LOGGER.debug("New request to /ordini");
        List<OrdinazioneDto> ordinazione = ordinazioneService.getAllOrdini();
        DeVeterisResponse<List<OrdinazioneDto>> response = new DeVeterisResponse<>();
        response.setBody(ordinazione);
        LOGGER.debug("Sending response from /persist");
        return ResponseEntity.ok(response);
    }

    @ApiOperation(value = "/staff/ordini/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, notes = "Aggiunge o modifica l'ordine")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Ordinazioni recuperata con successo", response = OrdinazioneDto.class),
            @ApiResponse(code = 500, message = "Errore di sistema"),
            @ApiResponse(code = 400, message = "Dati inviati incompleti o errati"),
            @ApiResponse(code = 401, message = "Utente non autorizzato"),
            @ApiResponse(code = 403, message = "L'utente non dispone delle autorizzazioni necessarie per eseguire l'operazione")
    })
    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DeVeterisResponse<OrdinazioneDto>> getOrdine(@PathVariable("id") Integer id) {
        LOGGER.debug("New request to /ordini/{}", id);
        OrdinazioneDto ordinazione = ordinazioneService.getOrdine(id);
        DeVeterisResponse<OrdinazioneDto> response = new DeVeterisResponse<>();
        response.setBody(ordinazione);
        LOGGER.debug("Sending response from /ordini/{}", id);
        return ResponseEntity.ok(response);
    }
}
