package magazzino.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import magazzino.enums.StatoOrdine;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "ordini")
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class Ordine extends BaseEntity {

    @Id
    private Integer id;

    @OneToMany(mappedBy = "ordine")
    private Set<OrdineMateriaPrima> ordiniMateriaPrima = new HashSet<>();

    @Enumerated(EnumType.STRING)
    @Column(name = "stato_ordine")
    private StatoOrdine statoOrdine;

    @Column(name = "data_ordine")
    private Date dataOrdine;

    @Column(name = "data_consegna")
    private Date dataConsegna;

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
