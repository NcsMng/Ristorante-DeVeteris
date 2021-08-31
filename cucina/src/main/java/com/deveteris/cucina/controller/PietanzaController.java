package com.deveteris.cucina.controller;

import com.deveteris.commons.response.DeVeterisResponse;
import com.deveteris.cucina.dto.PietanzaDto;
import com.deveteris.cucina.request.PietanzaRequest;
import com.deveteris.cucina.services.PietanzeService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/cucina/pietanze")
public class PietanzaController {
    private static final Logger LOGGER = LoggerFactory.getLogger(MenuController.class);
    private final PietanzeService pietanzeService;

    public PietanzaController(PietanzeService pietanzeService) {
        this.pietanzeService = pietanzeService;
    }

    @ApiOperation(value = "/cucina/pietanze/delete/{idPiatto}", produces = MediaType.APPLICATION_JSON_VALUE, notes = "Cancella pietanza dal menu generale")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Piatto dal menu cancellato correttamente", response = Integer.class),
            @ApiResponse(code = 500, message = "Errore di sistema"),
            @ApiResponse(code = 400, message = "Dati inviati incompleti o errati"),
            @ApiResponse(code = 401, message = "Utente non autorizzato"),
            @ApiResponse(code = 403, message = "L'utente non dispone delle autorizzazioni necessarie per eseguire l'operazione")
    })
    @PostMapping(path = "/delete/{idPiatto}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DeVeterisResponse<Void>> deletePiattoFromMenu(@PathVariable("idPiatto") String idPiatto){
        LOGGER.debug("New request to /delete/{idPiatto}");
        pietanzeService.deletePietanza(idPiatto);
        DeVeterisResponse<Void> response = new DeVeterisResponse<>();
        LOGGER.debug("Sending response from /delete/{idPiatto}");
        return ResponseEntity.ok(response);
    }

    @ApiOperation(value = "/cucina/pietanze/persist", produces = MediaType.APPLICATION_JSON_VALUE, notes = "Salva o modifica piatto dal menu generico. Id 5 CHAR necessario")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Menu cancellato correttamente", response = Boolean.class),
            @ApiResponse(code = 500, message = "Errore di sistema"),
            @ApiResponse(code = 400, message = "Dati inviati incompleti o errati"),
            @ApiResponse(code = 401, message = "Utente non autorizzato"),
            @ApiResponse(code = 403, message = "L'utente non dispone delle autorizzazioni necessarie per eseguire l'operazione")
    })
    @PostMapping(path = "/persist", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DeVeterisResponse<PietanzaDto>> persistPiatto(@RequestBody PietanzaRequest pietanzaRequest){
        LOGGER.debug("New request to /pietanze/persist");
        PietanzaDto pietanza = pietanzeService.persistPietanza(pietanzaRequest);
        DeVeterisResponse<PietanzaDto> response = new DeVeterisResponse<>();
        response.setBody(pietanza);
        LOGGER.debug("Sending response from /pietanze/persist");
        return ResponseEntity.ok(response);
    }
    @ApiOperation(value = "/cucina/pietanze", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, notes = "Recupera tutte le pietanze del menu generico")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Ordinazioni recuperate con successo", response = PietanzaDto.class),
            @ApiResponse(code = 500, message = "Errore di sistema"),
            @ApiResponse(code = 400, message = "Dati inviati incompleti o errati"),
            @ApiResponse(code = 401, message = "Utente non autorizzato"),
            @ApiResponse(code = 403, message = "L'utente non dispone delle autorizzazioni necessarie per eseguire l'operazione")
    })
    @GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DeVeterisResponse<Set<PietanzaDto>>> getAllPietanze() {
        LOGGER.debug("New request to /cucina/pietanze");
        Set<PietanzaDto> pietanze = pietanzeService.getAllPietanze();
        DeVeterisResponse<Set<PietanzaDto>> response = new DeVeterisResponse<>();
        response.setBody(pietanze);
        LOGGER.debug("Sending response from /cucina/pietanze");
        return ResponseEntity.ok(response);
    }

    @ApiOperation(value = "/cucina/pietanze/{idPietanza}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, notes = "Recupero pietanza per id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Ordinazioni recuperata con successo", response = PietanzaDto.class),
            @ApiResponse(code = 500, message = "Errore di sistema"),
            @ApiResponse(code = 400, message = "Dati inviati incompleti o errati"),
            @ApiResponse(code = 401, message = "Utente non autorizzato"),
            @ApiResponse(code = 403, message = "L'utente non dispone delle autorizzazioni necessarie per eseguire l'operazione")
    })
    @GetMapping(path = "/{idPietanza}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DeVeterisResponse<PietanzaDto>> getPietanza(@PathVariable("idPietanza") String id) {
        LOGGER.debug("New request to /cucina/pietanze/{idPietanza}");
        PietanzaDto pietanza = pietanzeService.getPietanzaById(id);
        DeVeterisResponse<PietanzaDto> response = new DeVeterisResponse<>();
        response.setBody(pietanza);
        LOGGER.debug("Sending response from /cucina/pietanze/{idPietanza}");
        return ResponseEntity.ok(response);
    }
}
