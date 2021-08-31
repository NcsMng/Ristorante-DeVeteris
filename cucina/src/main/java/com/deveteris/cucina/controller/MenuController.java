package com.deveteris.cucina.controller;

import com.deveteris.commons.response.DeVeterisResponse;
import com.deveteris.cucina.dto.PietanzaDto;
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

import java.util.HashSet;
import java.util.Set;
@RestController
@RequestMapping("/cucina/menu")
public class MenuController {
    private static final Logger LOGGER = LoggerFactory.getLogger(MenuController.class);
    private final MenuGiornoService menuGiornoService;

    public MenuController(MenuGiornoService menuGiornoService) {
        this.menuGiornoService = menuGiornoService;
    }

    @ApiOperation(value = "/cucina/menu/persist", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, notes = "Salva o modifica il menu")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Menu salvato correttamente", response = PersistMenuGiornoResponse.class),
            @ApiResponse(code = 500, message = "Errore di sistema"),
            @ApiResponse(code = 400, message = "Dati inviati incompleti o errati"),
            @ApiResponse(code = 401, message = "Utente non autorizzato"),
            @ApiResponse(code = 403, message = "L'utente non dispone delle autorizzazioni necessarie per eseguire l'operazione")
    })
    @PostMapping(path = "/persist", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DeVeterisResponse<PersistMenuGiornoResponse>> persistMenu(@RequestBody Set<MenuGiornoRequest> menuGiornoRequest){
        LOGGER.debug("New request to /menu/persist");
        PersistMenuGiornoResponse responseBody = menuGiornoService.persistMenu(new HashSet<>(menuGiornoRequest));
        DeVeterisResponse<PersistMenuGiornoResponse> response = new DeVeterisResponse<>();
        response.setBody(responseBody);
        LOGGER.debug("Sending response from /menu/persist");
        return ResponseEntity.ok(response);
    }

    @ApiOperation(value = "/cucina/menu/delete/{idPiatto}", produces = MediaType.APPLICATION_JSON_VALUE, notes = "Cancella piatto dal menu")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Piatto dal menu cancellato correttamente", response = Boolean.class),
            @ApiResponse(code = 500, message = "Errore di sistema"),
            @ApiResponse(code = 400, message = "Dati inviati incompleti o errati"),
            @ApiResponse(code = 401, message = "Utente non autorizzato"),
            @ApiResponse(code = 403, message = "L'utente non dispone delle autorizzazioni necessarie per eseguire l'operazione")
    })
    @PostMapping(path = "/delete/{idPiatto}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DeVeterisResponse<Boolean>> deletePiattiFromMenu(@PathVariable("idPiatto") String idPiatto){
        LOGGER.debug("New request to /delete/{idPiatto}");
        Boolean resultOfDelete = menuGiornoService.deletePiattoFromMenu(idPiatto);
        DeVeterisResponse<Boolean> response = new DeVeterisResponse<>();
        response.setBody(resultOfDelete);
        LOGGER.debug("Sending response from /delete/{idPiatto}");
        return ResponseEntity.ok(response);
    }

    @ApiOperation(value = "/cucina/menu/delete", produces = MediaType.APPLICATION_JSON_VALUE, notes = "Cancella il menu")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Menu cancellato correttamente", response = Boolean.class),
            @ApiResponse(code = 500, message = "Errore di sistema"),
            @ApiResponse(code = 400, message = "Dati inviati incompleti o errati"),
            @ApiResponse(code = 401, message = "Utente non autorizzato"),
            @ApiResponse(code = 403, message = "L'utente non dispone delle autorizzazioni necessarie per eseguire l'operazione")
    })
    @PostMapping(path = "/delete", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DeVeterisResponse<Boolean>> deletePiattiFromMenu(){
        LOGGER.debug("New request to /delete");
        Boolean deletedAll = menuGiornoService.deleteMenu();
        DeVeterisResponse<Boolean> response = new DeVeterisResponse<>();
        response.setBody(deletedAll);
        LOGGER.debug("Sending response from /delete");
        return ResponseEntity.ok(response);
    }

    @ApiOperation(value = "/cucina/menu/persist/{idPiatto}", produces = MediaType.APPLICATION_JSON_VALUE, notes = "Aggiunta piatto al menu")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Piatto aggiunto correttamente", response = Boolean.class),
            @ApiResponse(code = 500, message = "Errore di sistema"),
            @ApiResponse(code = 400, message = "Dati inviati incompleti o errati"),
            @ApiResponse(code = 401, message = "Utente non autorizzato"),
            @ApiResponse(code = 403, message = "L'utente non dispone delle autorizzazioni necessarie per eseguire l'operazione")
    })
    @PostMapping(path = "/persist/{idPiatto}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DeVeterisResponse<PietanzaDto>> persistPiatto(@PathVariable("idPiatto") String idPiatto){
        LOGGER.debug("New request to /persist/{idPiatto}");
        PietanzaDto pietanza = menuGiornoService.persistPietanza(idPiatto);
        DeVeterisResponse<PietanzaDto> response = new DeVeterisResponse<>();
        response.setBody(pietanza);
        LOGGER.debug("Sending response from /persist/{idPiatto}");
        return ResponseEntity.ok(response);
    }

}
