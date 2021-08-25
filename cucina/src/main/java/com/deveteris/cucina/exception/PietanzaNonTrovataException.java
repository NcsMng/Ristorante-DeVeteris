package com.deveteris.cucina.exception;

import com.deveteris.commons.exception.DeVeterisException;

public class PietanzaNonTrovataException extends DeVeterisException {
    public PietanzaNonTrovataException(String message, Object... pars) {
        super(message, pars);
    }
}
