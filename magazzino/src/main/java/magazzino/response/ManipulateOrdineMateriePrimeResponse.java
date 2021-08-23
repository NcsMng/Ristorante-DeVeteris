package magazzino.response;

import magazzino.model.Ordine;

import java.util.HashSet;
import java.util.Set;

public class ManipulateOrdineMateriePrimeResponse {
    private Ordine ordine;
    private Set<String> idMPNotFound = new HashSet<>();

    public ManipulateOrdineMateriePrimeResponse(Ordine ordine, Set<String> idMPNotFound) {
        this.ordine = ordine;
        this.idMPNotFound = idMPNotFound;
    }

    public ManipulateOrdineMateriePrimeResponse() {
    }

    public Ordine getOrdine() {
        return ordine;
    }

    public void setOrdine(Ordine ordine) {
        this.ordine = ordine;
    }

    public Set<String> getIdMPNotFound() {
        return idMPNotFound;
    }

    public void setIdMPNotFound(Set<String> idMPNotFound) {
        this.idMPNotFound = idMPNotFound;
    }
}
