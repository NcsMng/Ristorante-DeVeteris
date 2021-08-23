package magazzino.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import magazzino.model.Ordine;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class ManipulateOrdineMateriePrimeResponse {
    private Ordine ordine;
    private Set<String> idMPNotFound;
}
