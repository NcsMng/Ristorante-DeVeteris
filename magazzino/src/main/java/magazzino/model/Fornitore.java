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

@Entity(name = "fornitori")
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class Fornitore extends BaseEntity{
    @Id
    private Integer id;
    @Column(name = "nome")
    private String nome;
    @Column(name = "specializzazione")
    private String specializzazione;
    @OneToMany(mappedBy = "fornitore")
    private Set<OrdineMateriaPrima> ordiniMateriaPrima;
}
