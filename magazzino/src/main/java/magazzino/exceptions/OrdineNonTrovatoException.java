package magazzino.exceptions;

public class OrdineNonTrovatoException extends MagazzinoException{
    public OrdineNonTrovatoException(String message, Object... pars) {
        super(message, pars);
    }
}
