package com.deveteris.magazzino.exceptions;

import com.deveteris.commons.exception.DeVeterisException;

public class OrdineNonTrovatoException extends DeVeterisException {
    public OrdineNonTrovatoException(String message, Object... pars) {
        super(message, pars);
    }
}
