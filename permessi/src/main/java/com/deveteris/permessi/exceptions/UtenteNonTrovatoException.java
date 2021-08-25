package com.deveteris.permessi.exceptions;

public class UtenteNonTrovatoException extends PermessiException{
    public UtenteNonTrovatoException(String message, Object... pars) {
        super(message, pars);
    }
}
