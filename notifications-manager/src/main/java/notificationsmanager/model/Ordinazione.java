package notificationsmanager.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import notificationsmanager.enums.StatoOrdinazione;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class Ordinazione extends BaseEntity {

    @Id
    private Integer id;

    @Column(name = "note", length = 500)
    private String note;

    @Column(name = "costo")
    private double costo;

    @Column(name = "stato", nullable = false)
    @Enumerated(EnumType.STRING)
    private StatoOrdinazione stato = StatoOrdinazione.IN_ATTESA;

    @OneToMany(mappedBy="ordinazione")
    private Set<PiattoOrdinazione> piattiOrdinazione;
}
