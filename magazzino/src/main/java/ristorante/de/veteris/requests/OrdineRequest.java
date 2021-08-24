package ristorante.de.veteris.requests;

import java.util.Date;

public class OrdineRequest {
    private Integer id;
    private Date dataOrdine;
    private Date dataConsegna;

    public OrdineRequest(Integer id, Date dataOrdine, Date dataConsegna) {
        this.id = id;
        this.dataOrdine = dataOrdine;
        this.dataConsegna = dataConsegna;
    }
    public OrdineRequest(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDataOrdine() {
        return dataOrdine;
    }

    public void setDataOrdine(Date dataOrdine) {
        this.dataOrdine = dataOrdine;
    }

    public Date getDataConsegna() {
        return dataConsegna;
    }

    public void setDataConsegna(Date dataConsegna) {
        this.dataConsegna = dataConsegna;
    }
}
