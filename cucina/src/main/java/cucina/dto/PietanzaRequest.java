package cucina.dto;

import lombok.Data;


public class PietanzaRequest {
    private String id;
    private Double prezzo;
    private Integer tempoPreparazioneMinuti;
    private String nomePiatto;
    private String descrizione;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Double getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(Double prezzo) {
        this.prezzo = prezzo;
    }

    public Integer getTempoPreparazioneMinuti() {
        return tempoPreparazioneMinuti;
    }

    public void setTempoPreparazioneMinuti(Integer tempoPreparazioneMinuti) {
        this.tempoPreparazioneMinuti = tempoPreparazioneMinuti;
    }

    public String getNomePiatto() {
        return nomePiatto;
    }

    public void setNomePiatto(String nomePiatto) {
        this.nomePiatto = nomePiatto;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }
}
