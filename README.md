
# Ristorante-DeVeteris

Il ristorante **DeVeteris** è interessato a digitalizzare i suoi processi rendendoli più efficienti. Si vuole, più precisamente, digitalizzare tutto il processo dell’ordinazione e del take-away, automatizzando il rifornimento del magazzino in base a previsioni ottenute da un **sistema esperto** che le calcola osservando l’andamento dell’anno precedente, 
correggendole in base agli sprechi registrati mensilmente. 
Il magazziniere, il cuoco e il manager potranno comunque richiedere una quantità minore o maggiore di materia prima rispetto alla previsione.

# Ruoli richiesti

 - **GENERIC_USER**: client che deve ordinare con i servizi esposti. Solitamente un tablet al tavolo oppure il sito web del ristorante. Questo avrà accesso solo agli **endpoint** sotto 
**/client-services/clienti/ordini/****
 - **CAMERIERE**: la persona a disposizione dei clienti per eventuali problemi con gli ordini. 
 - **CUOCO**: la persona con maggior esperienza in cucina e di conseguenza egli ha varie **responsabilità** (gestire pietanze, menu del giorno e modificare ordini verso fornitori) e quindi **permessi**. In particolare, il cuoco avrà il permesso di accedere ai seguenti endpoints:
**/cucina/menu/****
**/cucina/pietanze/****
**/magazzino/ordini/manipulate (Tramite Netflix Zuul)**
 - **MAGAZZINIERE**: la persona che si occuperà della **gestione del magazzino**. Più in particolare egli potrà **gestire i fornitori, gli ordini verso questi e le materie prime**. Di conseguenza il magazziniere potrà accedere ai seguenti endpoint:
**/magazzino/fornitori/****
**/magazzino/materie-prime/****
**/magazzino/ordini/****
 - **MANAGER**: il manager del ristorante. In quanto la **persona con più responsabilità** egli deve avere i permessi di tutto il ristorante per poter intervenire con prontezza alle emergenze. Di conseguenza **egli potrà accedere a tutti gli endpoint** del ristorante.

# Flusso Applicazione
![image](https://user-images.githubusercontent.com/47748446/159024142-8871223c-47fe-426c-b529-a8f20c3afbf7.png)

# Implementazione
![image](https://user-images.githubusercontent.com/47748446/159024201-97d15f61-a85d-4fcc-b6a0-ac6653509f40.png)

# Struttura
La struttura dell’applicazione può essere trovata nel file **DeVeterisStructure.pdf** dove vengono evidenziate le interconnessioni delle applicazioni che compongono il progetto.

# Databases
La struttura dei database e quindi le relazioni tra le tabelle possono essere consultate nel file **DeVeterisDb.pdf**. Ogni applicazione ha un proprio DB per poter gestire al meglio i dati. Il broker ha un DB separato e spring-security si appoggia su un altro ancora. 

# Use Case Diagrams
Nel file **‘Diagramma dei casi d’uso.pdf’** si possono vedere i diagrammi use case per quanto riguarda l’ordinazione (e quindi l’interazione esistente tra il client e la cucina) e il processo di ordinazione del magazzino. Ognuno di questi mette in evidenza il ruolo dei service dell’app e mostrano il ruolo dei vari actor.

# Sequence Diagrams

Sono presentati i sequence diagram delle operazioni piu’ articolate all’interno dell’app. In particolare:

 - **Diagramma per la  degli ordini** materia prima ovvero l’invio della richiesta di creazione di un ordine oppure la sua modifica.
 - **Diagramma per il processo di ordinazione** take-away o tavolo da parte dei clienti. Viene descritta la dinamica del broker e la sua interazione con il client e la cucina
 - **Diagramma per il sistema esperto** che fa le predizioni della quantità materia prima da ordinare per ciascun mese per ciascuna materia prima

# Documentazione Aggiuntiva

Sono inoltre forniti i seguenti strumenti di documentazione: 

 - i **JavaDocs** dell’applicazione dove vengono descritte ciascuna classe dell’applicazione, specialmente i service, i quali hanno una descrizione aggiuntiva che spiega il funzionamento, ciò che prendono in input e l’eventuale ritorno
 - il progetto è stato configurato per permettere di raggiungere per ciascuna applicazione l’endpoint **Swagger** dove è possibile vedere i dettagli dei rest-controller che permettono l’esposizione degli altri endpoint.

# Test Progetto
Ogni applicazione ha all’interno le proprie classi di test dove sono stati realizzati i test per le funzionalità piu’ critiche e fondamentali del progetto. Il piano di test, quindi, implica il testing di queste funzionalità.
