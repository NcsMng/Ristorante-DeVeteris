package cucina.exception;

public class PietanzaNonTrovataException extends CucinaException {
    public PietanzaNonTrovataException(String message, Object... pars) {
        super(message, pars);
    }
}
