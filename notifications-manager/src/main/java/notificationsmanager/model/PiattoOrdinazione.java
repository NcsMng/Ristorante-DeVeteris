package notificationsmanager.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class PiattoOrdinazione extends BaseEntity {
    @Id
    private Integer id;

    @Column(name = "codice_piatto")
    private String codicePiatto;

    @Column(name = "quantita")
    private Integer quantita;

    @Column(name = "note")
    private String note;

    @ManyToOne
    @JoinColumn(name="id_ordinazione", nullable=false)
    private Ordinazione ordinazione;
}
