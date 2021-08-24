package ristorante.de.veteris.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import ristorante.de.veteris.enums.StatoOrdine;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "ordini")
public class Ordine extends BaseEntity implements Serializable {
    static final long serialVersionUID = 3123213L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @OneToMany(mappedBy = "ordine", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonBackReference
    private Set<OrdineMateriaPrima> ordiniMateriaPrima = new HashSet<>();

    @Enumerated(EnumType.STRING)
    @Column(name = "stato_ordine")
    private StatoOrdine statoOrdine;

    @Column(name = "data_ordine")
    private Date dataOrdine;

    @Column(name = "data_consegna")
    private Date dataConsegna;

    public Ordine(Integer id, Set<OrdineMateriaPrima> ordiniMateriaPrima, StatoOrdine statoOrdine, Date dataOrdine, Date dataConsegna) {
        this.id = id;
        this.ordiniMateriaPrima = ordiniMateriaPrima;
        this.statoOrdine = statoOrdine;
        this.dataOrdine = dataOrdine;
        this.dataConsegna = dataConsegna;
    }

    public Ordine() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Set<OrdineMateriaPrima> getOrdiniMateriaPrima() {
        return ordiniMateriaPrima;
    }

    public void setOrdiniMateriaPrima(Set<OrdineMateriaPrima> ordiniMateriaPrima) {
        this.ordiniMateriaPrima = ordiniMateriaPrima;
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


}
