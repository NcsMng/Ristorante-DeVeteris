package cucina.dto;

import lombok.Data;
import lombok.NonNull;

@Data
public class PietanzaRequest {
    private String id;
    @NonNull
    private Double prezzo;
    @NonNull
    private Integer tempoPreparazioneMinuti;
    @NonNull
    private String nomePiatto;

    private String descrizione;
}
