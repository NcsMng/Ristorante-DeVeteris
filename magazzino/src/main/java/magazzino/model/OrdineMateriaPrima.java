package magazzino.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import magazzino.enums.StatoOrdine;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "ordini_materia_prima")
@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class OrdineMateriaPrima {
    @Id
    private Integer id;

    @Column(name = "quantita_ordinata")
    private Double quantitaOrdinata;

    @Column(name = "data_ordinazione")
    private Date dataOrdinazione;

    @Column(name = "data_consegna")
    private Date dataConsegna;

    @Enumerated(EnumType.STRING)
    @Column(name = "stato_ordine")
    private StatoOrdine statoOrdine;

    @ManyToOne
    @JoinColumn(name = "id_ordine")
    private Ordine ordine;

    @ManyToOne
    @JoinColumn(name = "id_materia_prima")
    private MateriaPrima materiaPrima;

    @ManyToOne
    @JoinColumn(name = "id_fornitore")
    private Fornitore fornitore;
}
