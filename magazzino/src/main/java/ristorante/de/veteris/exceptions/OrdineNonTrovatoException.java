package ristorante.de.veteris.exceptions;

import ristorante.de.veteris.exception.DeVeterisException;

public class OrdineNonTrovatoException extends DeVeterisException {
    public OrdineNonTrovatoException(String message, Object... pars) {
        super(message, pars);
    }
}
