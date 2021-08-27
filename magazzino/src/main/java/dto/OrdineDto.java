package dto;

import com.deveteris.magazzino.enums.StatoOrdine;

import java.util.Date;
import java.util.Set;

public class OrdineDto {
    private Integer id;
    private StatoOrdine statoOrdine;
    private Date dataOrdine;
    private Date dataConsegna;
    private Set<MateriaPrimaDto> ordiniMateriaPrima;

    public OrdineDto(Integer id, StatoOrdine statoOrdine, Date dataOrdine, Date dataConsegna, Set<MateriaPrimaDto> ordiniMateriaPrima) {
        this.id = id;
        this.statoOrdine = statoOrdine;
        this.dataOrdine = dataOrdine;
        this.dataConsegna = dataConsegna;
        this.ordiniMateriaPrima = ordiniMateriaPrima;
    }

    public OrdineDto() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public StatoOrdine getStatoOrdine() {
        return statoOrdine;
    }

    public void setStatoOrdine(StatoOrdine statoOrdine) {
        this.statoOrdine = statoOrdine;
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

    public Set<MateriaPrimaDto> getOrdiniMateriaPrima() {
        return ordiniMateriaPrima;
    }

    public void setOrdiniMateriaPrima(Set<MateriaPrimaDto> ordiniMateriaPrima) {
        this.ordiniMateriaPrima = ordiniMateriaPrima;
    }
}
