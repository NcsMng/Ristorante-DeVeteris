package com.deveteris.notificationsmanager.exceptions;

import com.deveteris.commons.exception.DeVeterisException;

public class PiattoOrdinazioneNonTrovatoException extends DeVeterisException {
    public PiattoOrdinazioneNonTrovatoException(String message, Object... pars) {
        super(message, pars);
    }
}
