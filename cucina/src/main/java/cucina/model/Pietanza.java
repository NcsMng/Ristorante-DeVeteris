package cucina.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class Pietanza extends BaseEntity{

    @Id
    @Column(length = 5)
    private String id;

    @Column(name = "prezzo")
    private double prezzo;

    @Column(name = "tempo_preparazione_minuti")
    private Integer tempoPreparazioneMinuti;

    @Column(name = "nome_piatto")
    private String nomePiatto;

    @Column(name = "descrizione", length = 500)
    private String descrizione;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(double prezzo) {
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
