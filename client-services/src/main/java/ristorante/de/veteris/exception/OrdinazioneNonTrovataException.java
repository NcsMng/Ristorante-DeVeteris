package ristorante.de.veteris.exception;

public class OrdinazioneNonTrovataException extends DeVeterisException {
    public OrdinazioneNonTrovataException(String message, Object... pars) {
        super(message, pars);
    }
}
