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

@Entity(name = "materia_prima")
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class MateriaPrima extends BaseEntity{

    @Id
    private String id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "quantita")
    private Double quantita;

    @Column(name = "descrizione")
    private String descrizione;

    @OneToMany(mappedBy = "materiaPrima")
    private Set<OrdineMateriaPrima> ordiniMateriaPrima = new HashSet<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getQuantita() {
        return quantita;
    }

    public void setQuantita(Double quantita) {
        this.quantita = quantita;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public Set<OrdineMateriaPrima> getOrdiniMateriaPrima() {
        return ordiniMateriaPrima;
    }

    public void setOrdiniMateriaPrima(Set<OrdineMateriaPrima> ordiniMateriaPrima) {
        this.ordiniMateriaPrima = ordiniMateriaPrima;
    }
}
