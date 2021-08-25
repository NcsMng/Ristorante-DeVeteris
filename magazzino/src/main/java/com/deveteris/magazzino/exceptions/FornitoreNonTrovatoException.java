package com.deveteris.magazzino.exceptions;

import com.deveteris.commons.exception.DeVeterisException;

public class FornitoreNonTrovatoException extends DeVeterisException {
    public FornitoreNonTrovatoException(String message, Object... pars) {
        super(message, pars);
    }
}
