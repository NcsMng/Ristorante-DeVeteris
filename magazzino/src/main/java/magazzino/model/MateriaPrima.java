package magazzino.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity(name = "materia_prima")
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class MateriaPrima extends BaseEntity{

    @Id
    private String id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "quantita")
    private Double quantita;

    @Column(name = "descrizione")
    private String descrizione;

    @OneToMany(mappedBy = "materiaPrima")
    private Set<OrdineMateriaPrima> ordiniMateriaPrima;
}
