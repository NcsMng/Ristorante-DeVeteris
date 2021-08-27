package dto;

public class FornitoreDto {
    private Integer id;
    private String nome;
    private String specializzazione;

    public FornitoreDto(Integer id, String nome, String specializzazione) {
        this.id = id;
        this.nome = nome;
        this.specializzazione = specializzazione;
    }
    public FornitoreDto(){}

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
}
