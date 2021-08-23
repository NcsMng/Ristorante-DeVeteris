package cucina.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

@Entity
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class MenuGiorno extends BaseEntity{
    @Id
    private Integer id;

    @OneToMany
    @JoinColumn(name="id_pietanza")
    private Set<Pietanza> pietanze = new HashSet<>();

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
