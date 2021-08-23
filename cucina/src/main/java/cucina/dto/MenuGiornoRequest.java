package cucina.dto;

import lombok.Data;
import lombok.NonNull;

import java.util.Set;

@Data
public class MenuGiornoRequest {
    @NonNull
    private Set<PietanzaRequest> pietanze;
}
