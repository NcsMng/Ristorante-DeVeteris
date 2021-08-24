package ristorante.de.veteris.requests;

import java.util.Date;
import java.util.Map;

public class OrdineMateriaPrimaRequest {
    private Integer idOrdine; //idOrdine
    private Date dataOrdinazione;
    private Date dataConsegna;
    private Integer idFornitore;
    private Map<String,Double> idMateriePrimeQta;

    public OrdineMateriaPrimaRequest() {
    }

    public OrdineMateriaPrimaRequest(Integer idOrdine, Date dataOrdinazione, Date dataConsegna, Integer idFornitore, Map<String, Double> idMateriePrimeQta) {
        this.idOrdine = idOrdine;
        this.dataOrdinazione = dataOrdinazione;
        this.dataConsegna = dataConsegna;
        this.idFornitore = idFornitore;
        this.idMateriePrimeQta = idMateriePrimeQta;
    }

    public Integer getIdOrdine() {
        return idOrdine;
    }

    public void setIdOrdine(Integer idOrdine) {
        this.idOrdine = idOrdine;
    }

    public Date getDataOrdinazione() {
        return dataOrdinazione;
    }

    public void setDataOrdinazione(Date dataOrdinazione) {
        this.dataOrdinazione = dataOrdinazione;
    }

    public Date getDataConsegna() {
        return dataConsegna;
    }

    public void setDataConsegna(Date dataConsegna) {
        this.dataConsegna = dataConsegna;
    }

    public Integer getIdFornitore() {
        return idFornitore;
    }

    public void setIdFornitore(Integer idFornitore) {
        this.idFornitore = idFornitore;
    }

    public Map<String, Double> getIdMateriePrimeQta() {
        return idMateriePrimeQta;
    }

    public void setIdMateriePrimeQta(Map<String, Double> idMateriePrimeQta) {
        this.idMateriePrimeQta = idMateriePrimeQta;
    }
}
