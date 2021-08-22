package cucina.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class Pietanza extends BaseEntity{

    @Id
    @Column(length = 5)
    private String id;

    @Column(name = "prezzo")
    private double prezzo;

    @Column(name = "tempo_preparazione_minuti")
    private int tempoPreparazioneMinuti;

    @Column(name = "nome_piatto")
    private String nomePiatto;

    @Column(name = "descrizione", length = 500)
    private String descrizione;

}
