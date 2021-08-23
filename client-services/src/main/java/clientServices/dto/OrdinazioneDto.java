package clientServices.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import notificationsmanager.enums.StatoOrdinazione;
import notificationsmanager.model.PiattoOrdinazione;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
public class OrdinazioneDto {
    private Integer id;
    private String note;
    private double costo;
    private StatoOrdinazione stato;
    private Set<PiattoOrdinazione> piattiOrdinazione;

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
