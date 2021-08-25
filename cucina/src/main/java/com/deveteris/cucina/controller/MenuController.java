package com.deveteris.cucina.controller;

import com.deveteris.commons.response.DeVeterisResponse;
import com.deveteris.cucina.dto.MenuGiornoDto;
import com.deveteris.cucina.request.MenuGiornoRequest;
import com.deveteris.cucina.response.PersistMenuGiornoResponse;
import com.deveteris.cucina.services.MenuGiornoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cucina/menu")
public class MenuController {
    private static final Logger LOGGER = LoggerFactory.getLogger(MenuController.class);
    private final MenuGiornoService menuGiornoService;

    public MenuController(MenuGiornoService menuGiornoService) {
        this.menuGiornoService = menuGiornoService;
    }

    @ApiOperation(value = "/cucina/menu/persist", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, notes = "Aggiunge o modifica l'ordine")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Menu salvato correttamente", response = PersistMenuGiornoResponse.class),
            @ApiResponse(code = 500, message = "Errore di sistema"),
            @ApiResponse(code = 400, message = "Dati inviati incompleti o errati"),
            @ApiResponse(code = 401, message = "Utente non autorizzato"),
            @ApiResponse(code = 403, message = "L'utente non dispone delle autorizzazioni necessarie per eseguire l'operazione")
    })
    @PostMapping(path = "/persist", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DeVeterisResponse<PersistMenuGiornoResponse>> getOrdine(@RequestBody MenuGiornoRequest menuGiornoRequest){
        LOGGER.debug("New request to /menu/persist");
        PersistMenuGiornoResponse responseBody = menuGiornoService.persistMenu(menuGiornoRequest);
        DeVeterisResponse<PersistMenuGiornoResponse> response = new DeVeterisResponse<>();
        response.setBody(responseBody);
        LOGGER.debug("Sending response from /menu/persist");
        return ResponseEntity.ok(response);
    }

}
