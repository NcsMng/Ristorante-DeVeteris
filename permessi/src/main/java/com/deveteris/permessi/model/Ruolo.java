package com.deveteris.permessi.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "ruoli")
public class Ruolo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "nome")
    private String nome;

    @ManyToMany(mappedBy = "ruoli", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Utente> utenti = new HashSet<>();

    public Ruolo(){}
    public Ruolo(Integer id, String nome, Set<Utente> utenti) {
        this.id = id;
        this.nome = nome;
        this.utenti = utenti;
    }

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

    public Set<Utente> getUtenti() {
        return utenti;
    }

    public void setUtenti(Set<Utente> utenti) {
        this.utenti = utenti;
    }
}
