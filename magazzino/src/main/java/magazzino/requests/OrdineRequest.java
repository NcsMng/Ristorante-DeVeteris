package magazzino.requests;

import lombok.Data;

import java.util.Date;

@Data
public class OrdineRequest {
    private Integer id;
    private Date dataOrdine;
    private Date dataConsegna;
}
