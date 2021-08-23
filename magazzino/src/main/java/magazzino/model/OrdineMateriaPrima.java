package magazzino.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import magazzino.enums.StatoOrdine;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "ordini_materia_prima")
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class OrdineMateriaPrima {
    @Id
    private Integer id;

    @Column(name = "quantita_ordinata")
    private Double quantitaOrdinata;

    @ManyToOne
    @JoinColumn(name = "id_ordine")
    private Ordine ordine;

    @ManyToOne
    @JoinColumn(name = "id_materia_prima")
    private MateriaPrima materiaPrima;

    @ManyToOne
    @JoinColumn(name = "id_fornitore")
    private Fornitore fornitore;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getQuantitaOrdinata() {
        return quantitaOrdinata;
    }

    public void setQuantitaOrdinata(Double quantitaOrdinata) {
        this.quantitaOrdinata = quantitaOrdinata;
    }

    public Ordine getOrdine() {
        return ordine;
    }

    public void setOrdine(Ordine ordine) {
        this.ordine = ordine;
    }

    public MateriaPrima getMateriaPrima() {
        return materiaPrima;
    }

    public void setMateriaPrima(MateriaPrima materiaPrima) {
        this.materiaPrima = materiaPrima;
    }

    public Fornitore getFornitore() {
        return fornitore;
    }

    public void setFornitore(Fornitore fornitore) {
        this.fornitore = fornitore;
    }
}
