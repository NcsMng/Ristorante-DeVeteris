package com.deveteris.magazzino.controller;

import com.deveteris.commons.response.DeVeterisResponse;
import com.deveteris.magazzino.dto.OrdineDto;
import com.deveteris.magazzino.requests.OrdineMateriaPrimaRequest;
import com.deveteris.magazzino.requests.OrdineRequest;
import com.deveteris.magazzino.response.ManipulateOrdineMateriePrimeResponse;
import com.deveteris.magazzino.services.OrdiniService;
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
@RequestMapping("/magazzino/ordini")
public class OrdiniFornitoriController {
    private static final Logger LOGGER = LoggerFactory.getLogger(OrdiniFornitoriController.class);
    private final OrdiniService ordiniService;

    public OrdiniFornitoriController(OrdiniService ordiniService) {
        this.ordiniService = ordiniService;
    }

    @ApiOperation(value = "/magazzino/ordini/delete/{idOrdine}", produces = MediaType.APPLICATION_JSON_VALUE, notes = "Aggiunge o modifica l'ordine")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Ordine cancellato correttamente", response = Integer.class),
            @ApiResponse(code = 500, message = "Errore di sistema"),
            @ApiResponse(code = 400, message = "Dati inviati incompleti o errati"),
            @ApiResponse(code = 401, message = "Utente non autorizzato"),
            @ApiResponse(code = 403, message = "L'utente non dispone delle autorizzazioni necessarie per eseguire l'operazione")
    })
    @PostMapping(path = "/delete/{idOrdine}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DeVeterisResponse<Void>> deleteOrdine(@PathVariable("idOrdine") Integer idOrdine) {
        LOGGER.debug("New request to /delete/{idOrdine}");
        ordiniService.deleteOrdineById(idOrdine);
        DeVeterisResponse<Void> response = new DeVeterisResponse<>();
        LOGGER.debug("Sending response from /delete/{idOrdine}");
        return ResponseEntity.ok(response);
    }

    @ApiOperation(value = "/magazzino/ordini/persist", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE, notes = "Aggiunge o modifica l'ordine")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Ordine salvato correttamente", response = OrdineDto .class),
            @ApiResponse(code = 500, message = "Errore di sistema"),
            @ApiResponse(code = 400, message = "Dati inviati incompleti o errati"),
            @ApiResponse(code = 401, message = "Utente non autorizzato"),
            @ApiResponse(code = 403, message = "L'utente non dispone delle autorizzazioni necessarie per eseguire l'operazione")
    })
    @PostMapping(path = "/persist", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DeVeterisResponse<OrdineDto>> persistOrdine(@RequestBody OrdineRequest ordiniRequest) {
        LOGGER.debug("New request to /ordini/persist");
        OrdineDto ordini = ordiniService.persistOrdine(ordiniRequest);
        DeVeterisResponse<OrdineDto> response = new DeVeterisResponse<>();
        response.setBody(ordini);
        LOGGER.debug("Sending response from /ordini/persist");
        return ResponseEntity.ok(response);
    }

    @ApiOperation(value = "/magazzino/ordini", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, notes = "Aggiunge o modifica l'ordine")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Ordine recuperati con successo", response = OrdineDto.class),
            @ApiResponse(code = 500, message = "Errore di sistema"),
            @ApiResponse(code = 400, message = "Dati inviati incompleti o errati"),
            @ApiResponse(code = 401, message = "Utente non autorizzato"),
            @ApiResponse(code = 403, message = "L'utente non dispone delle autorizzazioni necessarie per eseguire l'operazione")
    })
    @GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DeVeterisResponse<Set<OrdineDto>>> getAllOrdine() {
        LOGGER.debug("New request to /magazzino/ordini");
        Set<OrdineDto> ordini = ordiniService.getAllOrdini();
        DeVeterisResponse<Set<OrdineDto>> response = new DeVeterisResponse<>();
        response.setBody(ordini);
        LOGGER.debug("Sending response from /magazzino/ordini");
        return ResponseEntity.ok(response);
    }

    @ApiOperation(value = "/magazzino/ordini/{idOrdine}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, notes = "Aggiunge o modifica l'ordine")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Ordine recuperato con successo", response = OrdineDto.class),
            @ApiResponse(code = 500, message = "Errore di sistema"),
            @ApiResponse(code = 400, message = "Dati inviati incompleti o errati"),
            @ApiResponse(code = 401, message = "Utente non autorizzato"),
            @ApiResponse(code = 403, message = "L'utente non dispone delle autorizzazioni necessarie per eseguire l'operazione")
    })
    @GetMapping(path = "/{idOrdine}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DeVeterisResponse<OrdineDto>> getOrdine(@PathVariable("idOrdine") Integer id) {
        LOGGER.debug("New request to /magazzino/ordini/{idOrdine}");
        OrdineDto ordini = ordiniService.getOrdineById(id);
        DeVeterisResponse<OrdineDto> response = new DeVeterisResponse<>();
        response.setBody(ordini);
        LOGGER.debug("Sending response from /magazzino/ordini/{idOrdine}");
        return ResponseEntity.ok(response);
    }

    @ApiOperation(value = "/magazzino/ordini/manipulate", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, notes = "Aggiunge o modifica l'ordine")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Ordine recuperato con successo", response = OrdineDto.class),
            @ApiResponse(code = 500, message = "Errore di sistema"),
            @ApiResponse(code = 400, message = "Dati inviati incompleti o errati"),
            @ApiResponse(code = 401, message = "Utente non autorizzato"),
            @ApiResponse(code = 403, message = "L'utente non dispone delle autorizzazioni necessarie per eseguire l'operazione")
    })
    @PostMapping(path = "/manipulate", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DeVeterisResponse<ManipulateOrdineMateriePrimeResponse>> manipulateOrdineMateriePrime(@RequestBody OrdineMateriaPrimaRequest request) {
        LOGGER.debug("New request to /magazzino/ordini/manipulate");
        ManipulateOrdineMateriePrimeResponse result = ordiniService.manipulateOrdineMateriePrime(request);
        DeVeterisResponse<ManipulateOrdineMateriePrimeResponse> response = new DeVeterisResponse<>();
        response.setBody(result);
        LOGGER.debug("Sending response from /magazzino/ordini/manipulate");
        return ResponseEntity.ok(response);
    }
}
