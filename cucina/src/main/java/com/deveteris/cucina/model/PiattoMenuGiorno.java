package com.deveteris.cucina.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "menu_giorno")
public class PiattoMenuGiorno extends BaseEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "id_pietanza",referencedColumnName = "id", unique = true, nullable = false)
    private Pietanza pietanza;

    public PiattoMenuGiorno(Integer id, Pietanza pietanza) {
        this.id = id;
        this.pietanza = pietanza;
    }

    public PiattoMenuGiorno() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Pietanza getPietanza() {
        return pietanza;
    }

    public void setPietanza(Pietanza pietanza) {
        this.pietanza = pietanza;
    }
}
