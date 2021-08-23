package clientServices.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ordini")
public class ClientController {
//    @ApiOperation(value = "/persist", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, notes = "Aggiunge o modifica l'ordine")
//    @ApiResponses({
//            @ApiResponse(code = 200, message = "Task recuperati con successo", response = TaskDto.class),
//            @ApiResponse(code = 500, message = "Errore di sistema"),
//            @ApiResponse(code = 400, message = "Dati inviati incompleti o errati"),
//            @ApiResponse(code = 401, message = "Utente non autorizzato"),
//            @ApiResponse(code = 403, message = "L'utente non dispone delle autorizzazioni necessarie per eseguire l'operazione")
//    })
//    @GetMapping(path = "", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//    public ResponseEntity<PortaleDistributoriResponse<Page<TaskDto>>> taskList(@NotBlank @RequestParam(required = false) String category,
//                                                                               @RequestParam(required = false) Stato statoUtente,
//                                                                               @RequestParam(required = false) String statoTask,
//                                                                               @NotBlank @RequestParam String agencyCode,
//                                                                               @NotBlank @RequestParam(required = false) String userid, Pageable pageable) throws PortaleDistributoriException {
}
