package clientServices.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class PiattoOrdinazioneDto {
    private String codicePiatto;
    private Integer quantita;
    private String note;

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
}
