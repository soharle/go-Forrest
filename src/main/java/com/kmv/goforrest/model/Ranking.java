package com.kmv.goforrest.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@Accessors(chain = true)

public class Ranking implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long codRanking;

    private String nome;

    @OneToMany(mappedBy = "ranking")
    private List<DistribuicaoDePontos> distribuicaoDePontos;

    @ManyToMany
    @JoinTable(
            name="Ranking_Evento",
            joinColumns=@JoinColumn(name="evento_codEvento"),
            inverseJoinColumns=@JoinColumn(name="ranking_codRanking")
    )
    private List<Evento> listaEventos;

    public Ranking() {
    }

}



