package magazzino.requests;

import lombok.Data;

import java.util.Date;
import java.util.Map;

@Data
public class OrdineMateriaPrimaRequest {
    private Integer idOrdine; //idOrdine
    private Date dataOrdinazione;
    private Date dataConsegna;
    private Integer idFornitore;
    private Map<String,Double> idMateriePrimeQta;
}
