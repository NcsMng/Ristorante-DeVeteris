package com.deveteris.magazzino.controller;

import com.deveteris.commons.response.DeVeterisResponse;
import com.deveteris.magazzino.dto.MateriaPrimaDto;
import com.deveteris.magazzino.dto.PrevisioneFabbisognoMpDto;
import com.deveteris.magazzino.requests.MateriaNonConsumataRequest;
import com.deveteris.magazzino.requests.MateriaPrimaRequest;
import com.deveteris.magazzino.services.MateriaPrimaService;
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
@RequestMapping("/magazzino/materie-prime")
public class MateriaPrimaController {
    private static final Logger LOGGER = LoggerFactory.getLogger(FornitoriController.class);
    private final MateriaPrimaService materiePrimeService;

    public MateriaPrimaController(MateriaPrimaService materiePrimeService) {
        this.materiePrimeService = materiePrimeService;
    }

    @ApiOperation(value = "/magazzino/materie-prime/delete/{idMateriaPrima}", produces = MediaType.APPLICATION_JSON_VALUE, notes = "Aggiunge o modifica l'ordine")
    @ApiResponses({
            @ApiResponse(code = 200, message = "MateriaPrima cancellato correttamente", response = Integer.class),
            @ApiResponse(code = 500, message = "Errore di sistema"),
            @ApiResponse(code = 400, message = "Dati inviati incompleti o errati"),
            @ApiResponse(code = 401, message = "Utente non autorizzato"),
            @ApiResponse(code = 403, message = "L'utente non dispone delle autorizzazioni necessarie per eseguire l'operazione")
    })
    @PostMapping(path = "/delete/{idMateriaPrima}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DeVeterisResponse<Void>> deleteMateriaPrima(@PathVariable("idMateriaPrima") String idMateriaPrima) {
        LOGGER.debug("New request to /delete/{idMateriaPrima}");
        materiePrimeService.deleteMateriaPrima(idMateriaPrima);
        DeVeterisResponse<Void> response = new DeVeterisResponse<>();
        LOGGER.debug("Sending response from /delete/{idMateriaPrima}");
        return ResponseEntity.ok(response);
    }

    @ApiOperation(value = "/magazzino/materie-prime/persist", produces = MediaType.APPLICATION_JSON_VALUE, notes = "Aggiunge o modifica l'ordine")
    @ApiResponses({
            @ApiResponse(code = 200, message = "MateriaPrima salvato correttamente", response = Boolean.class),
            @ApiResponse(code = 500, message = "Errore di sistema"),
            @ApiResponse(code = 400, message = "Dati inviati incompleti o errati"),
            @ApiResponse(code = 401, message = "Utente non autorizzato"),
            @ApiResponse(code = 403, message = "L'utente non dispone delle autorizzazioni necessarie per eseguire l'operazione")
    })
    @PostMapping(path = "/persist", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DeVeterisResponse<MateriaPrimaDto>> persistMateriaPrima(@RequestBody MateriaPrimaRequest materiePrimeRequest) {
        LOGGER.debug("New request to /materie-prime/persist");
        MateriaPrimaDto materiePrime = materiePrimeService.persistMateriaPrima(materiePrimeRequest);
        DeVeterisResponse<MateriaPrimaDto> response = new DeVeterisResponse<>();
        response.setBody(materiePrime);
        LOGGER.debug("Sending response from /materie-prime/persist");
        return ResponseEntity.ok(response);
    }

    @ApiOperation(value = "/magazzino/materie-prime", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, notes = "Aggiunge o modifica l'ordine")
    @ApiResponses({
            @ApiResponse(code = 200, message = "MateriaPrima recuperati con successo", response = MateriaPrimaDto.class),
            @ApiResponse(code = 500, message = "Errore di sistema"),
            @ApiResponse(code = 400, message = "Dati inviati incompleti o errati"),
            @ApiResponse(code = 401, message = "Utente non autorizzato"),
            @ApiResponse(code = 403, message = "L'utente non dispone delle autorizzazioni necessarie per eseguire l'operazione")
    })
    @GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DeVeterisResponse<Set<MateriaPrimaDto>>> getAllMateriaPrima() {
        LOGGER.debug("New request to /magazzino/materie-prime");
        Set<MateriaPrimaDto> materiePrime = materiePrimeService.getAllMateriePrime();
        DeVeterisResponse<Set<MateriaPrimaDto>> response = new DeVeterisResponse<>();
        response.setBody(materiePrime);
        LOGGER.debug("Sending response from /magazzino/materie-prime");
        return ResponseEntity.ok(response);
    }

    @ApiOperation(value = "/magazzino/materie-prime/{idMateriaPrima}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, notes = "Aggiunge o modifica l'ordine")
    @ApiResponses({
            @ApiResponse(code = 200, message = "MateriaPrima recuperato con successo", response = MateriaPrimaDto.class),
            @ApiResponse(code = 500, message = "Errore di sistema"),
            @ApiResponse(code = 400, message = "Dati inviati incompleti o errati"),
            @ApiResponse(code = 401, message = "Utente non autorizzato"),
            @ApiResponse(code = 403, message = "L'utente non dispone delle autorizzazioni necessarie per eseguire l'operazione")
    })
    @GetMapping(path = "/{idMateriaPrima}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DeVeterisResponse<MateriaPrimaDto>> getMateriaPrima(@PathVariable("idMateriaPrima") String id) {
        LOGGER.debug("New request to /magazzino/materie-prime/{idMateriaPrima}");
        MateriaPrimaDto materiePrime = materiePrimeService.getMateriaPrimaById(id);
        DeVeterisResponse<MateriaPrimaDto> response = new DeVeterisResponse<>();
        response.setBody(materiePrime);
        LOGGER.debug("Sending response from /magazzino/materie-prime/{idMateriaPrima}");
        return ResponseEntity.ok(response);
    }
    @ApiOperation(value = "/magazzino/materie-prime/non-consumate", produces = MediaType.APPLICATION_JSON_VALUE, notes = "Aggiunge o modifica l'ordine")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Materie prime non usate salvate correttamente", response = Boolean.class),
            @ApiResponse(code = 500, message = "Errore di sistema"),
            @ApiResponse(code = 400, message = "Dati inviati incompleti o errati"),
            @ApiResponse(code = 401, message = "Utente non autorizzato"),
            @ApiResponse(code = 403, message = "L'utente non dispone delle autorizzazioni necessarie per eseguire l'operazione")
    })
    @PostMapping(path = "/non-consumate", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DeVeterisResponse<Set<PrevisioneFabbisognoMpDto>>> materiePrimeNonUsate(@RequestBody Set<MateriaNonConsumataRequest> request) {
        LOGGER.debug("New request to /materie-prime/persist");
        Set<PrevisioneFabbisognoMpDto> fabbisognoMpDtos = materiePrimeService.insertMateriaPrimaNonConsumataFineMese(request);
        DeVeterisResponse<Set<PrevisioneFabbisognoMpDto>> response = new DeVeterisResponse<>();
        response.setBody(fabbisognoMpDtos);
        LOGGER.debug("Sending response from /materie-prime/non-consumate");
        return ResponseEntity.ok(response);
    }
}
