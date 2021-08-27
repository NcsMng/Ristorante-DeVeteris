package dto;

import com.deveteris.magazzino.model.Ordine;

public class OrdiniMateriaPrimaDto {
    private Integer id;
    private Double quantitaOrdinata;
    private Ordine ordine;
    private MateriaPrimaDto materiaPrima;
    private FornitoreDto fornitore;

    public OrdiniMateriaPrimaDto(Integer id, Double quantitaOrdinata, Ordine ordine, MateriaPrimaDto materiaPrima, FornitoreDto fornitore) {
        this.id = id;
        this.quantitaOrdinata = quantitaOrdinata;
        this.ordine = ordine;
        this.materiaPrima = materiaPrima;
        this.fornitore = fornitore;
    }

    public OrdiniMateriaPrimaDto(){
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getQuantitaOrdinata() {
        return quantitaOrdinata;
    }

    public void setQuantitaOrdinata(Double quantitaOrdinata) {
        this.quantitaOrdinata = quantitaOrdinata;
    }

    public Ordine getOrdine() {
        return ordine;
    }

    public void setOrdine(Ordine ordine) {
        this.ordine = ordine;
    }

    public MateriaPrimaDto getMateriaPrima() {
        return materiaPrima;
    }

    public void setMateriaPrima(MateriaPrimaDto materiaPrima) {
        this.materiaPrima = materiaPrima;
    }

    public FornitoreDto getFornitore() {
        return fornitore;
    }

    public void setFornitore(FornitoreDto fornitore) {
        this.fornitore = fornitore;
    }
}
