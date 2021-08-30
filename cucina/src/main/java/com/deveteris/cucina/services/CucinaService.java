package com.deveteris.cucina.services;

public interface CucinaService {

    /**
     * metodo asincrono scheduled usato per processare le notifiche di ordini dalla cucina mettedoli in stato "IN_CARICO","PRONTO","ERORRE".
     * stato: "IN_CARICO" -> ordinazione presa in carico
     * stato: "PRONTO" -> ordinazione pronta per essere consegnata al cliente.
     *  Tempo preparazione piatto < tempo passato da quando l'ordinazione e' stata presa in carico
     * stato: "ERORRE"-> se uno dei piatti dell'ordinazione non esiste nel menu normale oppure in quello giornaliero
     */
    void processNotifications();

}
