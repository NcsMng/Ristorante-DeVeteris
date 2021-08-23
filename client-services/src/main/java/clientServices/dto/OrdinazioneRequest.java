package clientServices.dto;

import lombok.Data;
import lombok.NonNull;

import java.util.List;

public class OrdinazioneRequest {
    private Integer id;
    private String note;
    @NonNull
    private List<PiattoOrdinazioneDto> piattiOrdinazione;

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

    public List<PiattoOrdinazioneDto> getPiattiOrdinazione() {
        return piattiOrdinazione;
    }

    public void setPiattiOrdinazione(List<PiattoOrdinazioneDto> piattiOrdinazione) {
        this.piattiOrdinazione = piattiOrdinazione;
    }
}
