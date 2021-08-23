package clientServices.dto;

import java.util.List;

public class OrdinazioneRequest {
    private Integer id;
    private String note;
    private List<PiattoOrdinazioneRequest> piattiOrdinazione;

    public OrdinazioneRequest(Integer id, String note, List<PiattoOrdinazioneRequest> piattiOrdinazione) {
        this.id = id;
        this.note = note;
        this.piattiOrdinazione = piattiOrdinazione;
    }
    public OrdinazioneRequest(){}
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

    public List<PiattoOrdinazioneRequest> getPiattiOrdinazione() {
        return piattiOrdinazione;
    }

    public void setPiattiOrdinazione(List<PiattoOrdinazioneRequest> piattiOrdinazione) {
        this.piattiOrdinazione = piattiOrdinazione;
    }
}
