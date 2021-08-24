package ristorante.de.veteris.exceptions;

public class UtenteNonTrovatoException extends PermessiException{
    public UtenteNonTrovatoException(String message, Object... pars) {
        super(message, pars);
    }
}
