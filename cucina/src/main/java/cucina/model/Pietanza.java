package cucina.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Pietanza extends BaseEntity {

    @Id
    @Column(length = 5)
    private String id;

    @Column(name = "prezzo")
    private Double prezzo;

    @Column(name = "tempo_preparazione_minuti")
    private Integer tempoPreparazioneMinuti;

    @Column(name = "nome_piatto")
    private String nomePiatto;

    @Column(name = "descrizione", length = 500)
    private String descrizione;

    public Pietanza(String id, Double prezzo, Integer tempoPreparazioneMinuti, String nomePiatto, String descrizione) {
        this.id = id;
        this.prezzo = prezzo;
        this.tempoPreparazioneMinuti = tempoPreparazioneMinuti;
        this.nomePiatto = nomePiatto;
        this.descrizione = descrizione;
    }

    public Pietanza() {
    }

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
