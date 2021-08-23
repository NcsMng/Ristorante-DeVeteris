package notificationsmanager.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import notificationsmanager.enums.StatoOrdinazione;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class Ordinazione extends BaseEntity {

    @Id
    private Integer id;

    @Column(name = "note", length = 500)
    private String note;

    @Transient
    private final String uuid = UUID.randomUUID().toString();

    @Column(name = "costo")
    private double costo;

    @Column(name = "stato", nullable = false)
    @Enumerated(EnumType.STRING)
    private StatoOrdinazione stato = StatoOrdinazione.IN_ATTESA;

    @OneToMany(mappedBy="ordinazione")
    private Set<PiattoOrdinazione> piattiOrdinazione;
}
