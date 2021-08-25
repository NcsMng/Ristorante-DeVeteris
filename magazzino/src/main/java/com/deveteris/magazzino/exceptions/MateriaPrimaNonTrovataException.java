package com.deveteris.magazzino.exceptions;

import com.deveteris.commons.exception.DeVeterisException;

public class MateriaPrimaNonTrovataException extends DeVeterisException {
    public MateriaPrimaNonTrovataException(String message, Object... pars) {
        super(message, pars);
    }
}
