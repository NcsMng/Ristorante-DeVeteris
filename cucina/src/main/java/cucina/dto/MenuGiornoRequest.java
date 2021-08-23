package cucina.dto;

import lombok.Data;
import lombok.NonNull;

import java.util.Set;


public class MenuGiornoRequest {
    @NonNull
    private Set<PietanzaRequest> pietanze;

    public Set<PietanzaRequest> getPietanze() {
        return pietanze;
    }

    public void setPietanze(Set<PietanzaRequest> pietanze) {
        this.pietanze = pietanze;
    }
}
