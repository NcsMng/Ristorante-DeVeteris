package com.deveteris.magazzino.controller;

import com.deveteris.commons.response.DeVeterisResponse;
import com.deveteris.magazzino.dto.FornitoreDto;
import com.deveteris.magazzino.requests.FornitoreRequest;
import com.deveteris.magazzino.services.FornitoriService;
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
@RequestMapping("/magazzino/fornitori")

public class FornitoriController {

    private static final Logger LOGGER = LoggerFactory.getLogger(FornitoriController.class);
    private final FornitoriService fornitoriService;

    public FornitoriController(FornitoriService fornitoriService) {
        this.fornitoriService = fornitoriService;
    }

    @ApiOperation(value = "/magazzino/fornitori/delete/{idFornitore}", produces = MediaType.APPLICATION_JSON_VALUE, notes = "Aggiunge o modifica l'ordine")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Fornitore cancellato correttamente", response = Integer.class),
            @ApiResponse(code = 500, message = "Errore di sistema"),
            @ApiResponse(code = 400, message = "Dati inviati incompleti o errati"),
            @ApiResponse(code = 401, message = "Utente non autorizzato"),
            @ApiResponse(code = 403, message = "L'utente non dispone delle autorizzazioni necessarie per eseguire l'operazione")
    })
    @PostMapping(path = "/delete/{idFornitore}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DeVeterisResponse<Void>> deleteFornitoreFromMenu(@PathVariable("idFornitore") Integer idFornitore) {
        LOGGER.debug("New request to /delete/{idFornitore}");
        fornitoriService.deleteFornitore(idFornitore);
        DeVeterisResponse<Void> response = new DeVeterisResponse<>();
        LOGGER.debug("Sending response from /delete/{idFornitore}");
        return ResponseEntity.ok(response);
    }

    @ApiOperation(value = "/magazzino/fornitori/persist", produces = MediaType.APPLICATION_JSON_VALUE, notes = "Aggiunge o modifica l'ordine")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Fornitore salvato correttamente", response = Boolean.class),
            @ApiResponse(code = 500, message = "Errore di sistema"),
            @ApiResponse(code = 400, message = "Dati inviati incompleti o errati"),
            @ApiResponse(code = 401, message = "Utente non autorizzato"),
            @ApiResponse(code = 403, message = "L'utente non dispone delle autorizzazioni necessarie per eseguire l'operazione")
    })
    @PostMapping(path = "/persist", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DeVeterisResponse<FornitoreDto>> persistFornitore(@RequestBody FornitoreRequest fornitoreRequest) {
        LOGGER.debug("New request to /fornitori/persist");
        FornitoreDto fornitore = fornitoriService.persistFornitore(fornitoreRequest);
        DeVeterisResponse<FornitoreDto> response = new DeVeterisResponse<>();
        response.setBody(fornitore);
        LOGGER.debug("Sending response from /fornitori/persist");
        return ResponseEntity.ok(response);
    }

    @ApiOperation(value = "/magazzino/fornitori", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, notes = "Aggiunge o modifica l'ordine")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Fornitori recuperati con successo", response = FornitoreDto.class),
            @ApiResponse(code = 500, message = "Errore di sistema"),
            @ApiResponse(code = 400, message = "Dati inviati incompleti o errati"),
            @ApiResponse(code = 401, message = "Utente non autorizzato"),
            @ApiResponse(code = 403, message = "L'utente non dispone delle autorizzazioni necessarie per eseguire l'operazione")
    })
    @GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DeVeterisResponse<Set<FornitoreDto>>> getAllFornitori() {
        LOGGER.debug("New request to /magazzino/fornitori");
        Set<FornitoreDto> fornitori = fornitoriService.getAllFornitori();
        DeVeterisResponse<Set<FornitoreDto>> response = new DeVeterisResponse<>();
        response.setBody(fornitori);
        LOGGER.debug("Sending response from /magazzino/fornitori");
        return ResponseEntity.ok(response);
    }

    @ApiOperation(value = "/magazzino/fornitori/{idFornitore}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, notes = "Aggiunge o modifica l'ordine")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Fornitore recuperato con successo", response = FornitoreDto.class),
            @ApiResponse(code = 500, message = "Errore di sistema"),
            @ApiResponse(code = 400, message = "Dati inviati incompleti o errati"),
            @ApiResponse(code = 401, message = "Utente non autorizzato"),
            @ApiResponse(code = 403, message = "L'utente non dispone delle autorizzazioni necessarie per eseguire l'operazione")
    })
    @GetMapping(path = "/{idFornitore}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DeVeterisResponse<FornitoreDto>> getFornitore(@PathVariable("idFornitore") Integer id) {
        LOGGER.debug("New request to /magazzino/fornitori/{idFornitore}");
        FornitoreDto fornitore = fornitoriService.getFornitoreById(id);
        DeVeterisResponse<FornitoreDto> response = new DeVeterisResponse<>();
        response.setBody(fornitore);
        LOGGER.debug("Sending response from /magazzino/fornitori/{idFornitore}");
        return ResponseEntity.ok(response);
    }
}
