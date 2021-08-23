package magazzino.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity(name = "ordini")
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class Ordine extends BaseEntity {

    @Id
    private Integer id;

    @OneToMany(mappedBy = "ordine")
    private Set<OrdineMateriaPrima> ordiniMateriaPrima;

}
