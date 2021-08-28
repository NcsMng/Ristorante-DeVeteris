package com.deveteris.magazzino.controller;

import com.deveteris.commons.exception.DeVeterisException;
import com.deveteris.commons.response.DeVeterisResponse;
import com.deveteris.commons.response.Message;
import com.deveteris.magazzino.exceptions.FornitoreNonTrovatoException;
import com.deveteris.magazzino.exceptions.MateriaPrimaNonTrovataException;
import com.deveteris.magazzino.exceptions.OrdineNonTrovatoException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class DeVeterisExceptionController {
    private static final Logger LOGGER = LoggerFactory.getLogger(DeVeterisExceptionController.class);

    @ExceptionHandler(value
            = {
            FornitoreNonTrovatoException.class,
            MateriaPrimaNonTrovataException.class,
            OrdineNonTrovatoException.class
    })
    protected ResponseEntity<DeVeterisResponse<Object>> handleNotFound(DeVeterisException ex, WebRequest request) {
        LOGGER.error(ex.getMessage(), ex);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new DeVeterisResponse<>(getMessageFromRuntimeException(ex, HttpStatus.NOT_FOUND)));
    }

    @ExceptionHandler({DeVeterisException.class})
    protected ResponseEntity<DeVeterisResponse<Object>> handleGenericDeVeterisException(DeVeterisException ex, WebRequest request) {
        LOGGER.error(ex.getMessage(), ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new DeVeterisResponse<>(getMessageFromRuntimeException(ex, HttpStatus.INTERNAL_SERVER_ERROR)));
    }

    @ExceptionHandler({RuntimeException.class})
    protected ResponseEntity<DeVeterisResponse<Object>> handleRunTime(RuntimeException ex, WebRequest request) {
        LOGGER.error(ex.getMessage(), ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new DeVeterisResponse<>(getMessageFromRuntimeException(ex, HttpStatus.INTERNAL_SERVER_ERROR)));
    }

    @ExceptionHandler({Exception.class})
    protected ResponseEntity<DeVeterisResponse<Object>> handleGeneric(RuntimeException ex, WebRequest request) {
        LOGGER.error(ex.getMessage(), ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new DeVeterisResponse<>(getMessageFromRuntimeException(ex, HttpStatus.INTERNAL_SERVER_ERROR)));
    }


    private Message getMessageFromRuntimeException(RuntimeException ex, HttpStatus httpStatus) {
        return new Message(httpStatus.name(), ex.getLocalizedMessage());
    }


}
