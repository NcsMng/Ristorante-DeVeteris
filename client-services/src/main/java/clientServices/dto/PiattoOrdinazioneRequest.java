package clientServices.dto;

import lombok.Data;
import lombok.NonNull;

public class PiattoOrdinazioneRequest {
    @NonNull
    private String codicePiatto;
    @NonNull
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

