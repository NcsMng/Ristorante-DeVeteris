package magazzino.exceptions;

public class FornitoreNonTrovatoException extends MagazzinoException{
    public FornitoreNonTrovatoException(String message, Object... pars) {
        super(message, pars);
    }
}
