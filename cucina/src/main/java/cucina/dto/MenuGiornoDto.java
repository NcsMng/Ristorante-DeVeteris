package cucina.dto;

import java.util.Set;

public class MenuGiornoDto {
    private Integer id;
    private Set<PietanzaDto> pietanze;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Set<PietanzaDto> getPietanze() {
        return pietanze;
    }

    public void setPietanze(Set<PietanzaDto> pietanze) {
        this.pietanze = pietanze;
    }
}
