package magazzino.exceptions;

public class MateriaPrimaNonTrovataException extends MagazzinoException{
    public MateriaPrimaNonTrovataException(String message, Object... pars) {
        super(message, pars);
    }
}
