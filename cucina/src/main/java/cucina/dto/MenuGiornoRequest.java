package cucina.dto;

import java.util.Set;


public class MenuGiornoRequest {
    private Set<PietanzaRequest> pietanze;

    public MenuGiornoRequest(Set<PietanzaRequest> pietanze) {
        this.pietanze = pietanze;
    }

    public MenuGiornoRequest() {
    }

    public Set<PietanzaRequest> getPietanze() {
        return pietanze;
    }

    public void setPietanze(Set<PietanzaRequest> pietanze) {
        this.pietanze = pietanze;
    }
}
