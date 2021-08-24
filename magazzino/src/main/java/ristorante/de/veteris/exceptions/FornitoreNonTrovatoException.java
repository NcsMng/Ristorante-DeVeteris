package ristorante.de.veteris.exceptions;

import ristorante.de.veteris.exception.DeVeterisException;

public class FornitoreNonTrovatoException extends DeVeterisException {
    public FornitoreNonTrovatoException(String message, Object... pars) {
        super(message, pars);
    }
}
