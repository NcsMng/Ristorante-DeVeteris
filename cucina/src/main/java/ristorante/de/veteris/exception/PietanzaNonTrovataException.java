package ristorante.de.veteris.exception;

import ristorante.de.veteris.exception.DeVeterisException;

public class PietanzaNonTrovataException extends DeVeterisException {
    public PietanzaNonTrovataException(String message, Object... pars) {
        super(message, pars);
    }
}
