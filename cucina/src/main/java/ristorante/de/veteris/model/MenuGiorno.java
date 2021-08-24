package ristorante.de.veteris.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

@Entity
public class MenuGiorno extends BaseEntity {
    @Id
    private Integer id;

    @OneToMany
    @JoinColumn(name = "id_pietanza")
    private Set<Pietanza> pietanze = new HashSet<>();

    public MenuGiorno(Integer id, Set<Pietanza> pietanze) {
        this.id = id;
        this.pietanze = pietanze;
    }

    public MenuGiorno() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Set<Pietanza> getPietanze() {
        return pietanze;
    }

    public void setPietanze(Set<Pietanza> pietanze) {
        this.pietanze = pietanze;
    }
}
