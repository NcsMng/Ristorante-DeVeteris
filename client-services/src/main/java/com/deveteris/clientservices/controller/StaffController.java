package com.deveteris.clientservices.controller;

import com.deveteris.clientservices.dto.OrdinazioneDto;
import com.deveteris.clientservices.request.OrdinazioneRequest;
import com.deveteris.clientservices.services.OrdinazioneService;
import com.deveteris.commons.response.DeVeterisResponse;
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
@RequestMapping("/client-services/staff/ordini")
public class StaffController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ClientController.class);

    private final OrdinazioneService ordinazioneService;

    public StaffController(OrdinazioneService ordinazioneService) {
        this.ordinazioneService = ordinazioneService;
    }

    @ApiOperation(value = "/client-services/staff/ordini/delete/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, notes = "Cancellazione ordine per id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Ordinazione cancellata con successo", response = Boolean.class),
            @ApiResponse(code = 500, message = "Errore di sistema"),
            @ApiResponse(code = 400, message = "Dati inviati incompleti o errati"),
            @ApiResponse(code = 401, message = "Utente non autorizzato"),
            @ApiResponse(code = 403, message = "L'utente non dispone delle autorizzazioni necessarie per eseguire l'operazione")
    })
    @PostMapping(path = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DeVeterisResponse<Boolean>> deleteOrdinazione(@PathVariable("id") Integer id) {
        LOGGER.debug("New request to /staff/ordini/delete");
        Boolean ordinazione = ordinazioneService.deleteOrdinazione(id);
        DeVeterisResponse<Boolean> response = new DeVeterisResponse<>();
        response.setBody(ordinazione);
        LOGGER.debug("Sending response from /staff/ordini/delete");
        return ResponseEntity.ok(response);
    }

    @ApiOperation(value = "/client-services/staff/ordini/persist", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, notes = "Salva o modifica l'ordinazione se l'id e' passato")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Ordinazione salvata con successo", response = OrdinazioneDto.class),
            @ApiResponse(code = 500, message = "Errore di sistema"),
            @ApiResponse(code = 400, message = "Dati inviati incompleti o errati"),
            @ApiResponse(code = 401, message = "Utente non autorizzato"),
            @ApiResponse(code = 403, message = "L'utente non dispone delle autorizzazioni necessarie per eseguire l'operazione")
    })
    @PostMapping(path = "/persist", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DeVeterisResponse<OrdinazioneDto>> persistOrdinazione(@RequestBody OrdinazioneRequest request) {
        LOGGER.debug("New request to /staff/ordini/persist");
        OrdinazioneDto ordinazione = ordinazioneService.persistOrdinazione(request);
        DeVeterisResponse<OrdinazioneDto> response = new DeVeterisResponse<>();
        response.setBody(ordinazione);
        LOGGER.debug("Sending response from /staff/ordini/persist");
        return ResponseEntity.ok(response);
    }

    @ApiOperation(value = "/client-services/staff/ordini", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, notes = "Recupero di tutti gli ordini")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Ordinazioni recuperate con successo", response = OrdinazioneDto.class),
            @ApiResponse(code = 500, message = "Errore di sistema"),
            @ApiResponse(code = 400, message = "Dati inviati incompleti o errati"),
            @ApiResponse(code = 401, message = "Utente non autorizzato"),
            @ApiResponse(code = 403, message = "L'utente non dispone delle autorizzazioni necessarie per eseguire l'operazione")
    })
    @GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DeVeterisResponse<List<OrdinazioneDto>>> getAllOrdine() {
        LOGGER.debug("New request to /staff/ordini");
        List<OrdinazioneDto> ordinazione = ordinazioneService.getAllOrdini();
        DeVeterisResponse<List<OrdinazioneDto>> response = new DeVeterisResponse<>();
        response.setBody(ordinazione);
        LOGGER.debug("Sending response from /staff/ordini");
        return ResponseEntity.ok(response);
    }

    @ApiOperation(value = "/client-services/staff/ordini/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, notes = "Recupero ordine per id")
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
