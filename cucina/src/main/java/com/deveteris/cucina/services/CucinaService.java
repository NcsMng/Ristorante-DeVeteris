package com.deveteris.cucina.services;

public interface CucinaService {

    /**
     * metodo asincrono scheduled usato per processare le notifiche di ordini dalla cucina mettedoli in stato "IN_CARICO","PRONTO","ERORRE".
     * stato: "IN_CARICO" -&gt; ordinazione presa in carico
     * stato: "PRONTO" -&gt; ordinazione pronta per essere consegnata al cliente.
     *  Tempo preparazione piatto &lt; tempo passato da quando l'ordinazione e' stata presa in carico
     * stato: "ERORRE" -&gt; se uno dei piatti dell'ordinazione non esiste nel menu normale oppure in quello giornaliero
     */
    void processNotifications();

}
