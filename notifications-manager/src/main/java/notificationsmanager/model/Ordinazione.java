package notificationsmanager.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import notificationsmanager.enums.StatoOrdinazione;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
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
    private Set<PiattoOrdinazione> piattiOrdinazione = new HashSet<>();


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getUuid() {
        return uuid;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public StatoOrdinazione getStato() {
        return stato;
    }

    public void setStato(StatoOrdinazione stato) {
        this.stato = stato;
    }

    public Set<PiattoOrdinazione> getPiattiOrdinazione() {
        return piattiOrdinazione;
    }

    public void setPiattiOrdinazione(Set<PiattoOrdinazione> piattiOrdinazione) {
        this.piattiOrdinazione = piattiOrdinazione;
    }
}
