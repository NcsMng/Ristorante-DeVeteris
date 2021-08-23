package magazzino.requests;

import lombok.Data;

@Data
public class MateriaPrimaRequest {
    private String id;
    private String nome;
    private Double quantita;
    private String descrizione;
}
