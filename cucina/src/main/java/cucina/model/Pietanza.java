package cucina.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
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

}
