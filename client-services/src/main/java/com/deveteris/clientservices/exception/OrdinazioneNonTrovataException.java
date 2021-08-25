package com.deveteris.clientservices.exception;

import com.deveteris.commons.exception.DeVeterisException;

public class OrdinazioneNonTrovataException extends DeVeterisException {
    public OrdinazioneNonTrovataException(String message, Object... pars) {
        super(message, pars);
    }
}
