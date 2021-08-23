package magazzino.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "fornitori")
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class Fornitore extends BaseEntity{
    @Id
    private Integer id;
    @Column(name = "nome")
    private String nome;
    @Column(name = "specializzazione")
    private String specializzazione;
    @OneToMany(mappedBy = "fornitore")
    private Set<OrdineMateriaPrima> ordiniMateriaPrima = new HashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSpecializzazione() {
        return specializzazione;
    }

    public void setSpecializzazione(String specializzazione) {
        this.specializzazione = specializzazione;
    }

    public Set<OrdineMateriaPrima> getOrdiniMateriaPrima() {
        return ordiniMateriaPrima;
    }

    public void setOrdiniMateriaPrima(Set<OrdineMateriaPrima> ordiniMateriaPrima) {
        this.ordiniMateriaPrima = ordiniMateriaPrima;
    }
}
