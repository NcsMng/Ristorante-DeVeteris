package notificationsmanager.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCodicePiatto() {
        return codicePiatto;
    }

    public void setCodicePiatto(String codicePiatto) {
        this.codicePiatto = codicePiatto;
    }

    public Integer getQuantita() {
        return quantita;
    }

    public void setQuantita(Integer quantita) {
        this.quantita = quantita;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Ordinazione getOrdinazione() {
        return ordinazione;
    }

    public void setOrdinazione(Ordinazione ordinazione) {
        this.ordinazione = ordinazione;
    }
}
