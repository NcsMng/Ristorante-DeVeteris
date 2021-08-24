package ristorante.de.veteris.exceptions;

import ristorante.de.veteris.exception.DeVeterisException;

public class MateriaPrimaNonTrovataException extends DeVeterisException {
    public MateriaPrimaNonTrovataException(String message, Object... pars) {
        super(message, pars);
    }
}
