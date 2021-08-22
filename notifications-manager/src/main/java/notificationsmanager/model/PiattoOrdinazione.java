package notificationsmanager.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class PiattoOrdinazione extends BaseEntity {
    @Id
    private int id;

    @Column(name = "codice_piatto")
    private String codicePiatto;

    @Column(name = "quantita")
    private int quantita;

    @Column(name = "note")
    private String note;

    @ManyToOne
    @JoinColumn(name="id_ordinazione", nullable=false)
    private Ordinazione ordinazione;
}
