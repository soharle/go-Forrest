package com.kmv.goforrest.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;


@Entity
@Accessors(chain = true)
@Getter
@Setter
public class Evento implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long codEvento;

    private String titulo;
    private String linkMapa;
    private String localLargada;
    private String duracao;
    private String dataEvento;
    private String horalargada;
    private int maxParticipantes;

    @ManyToOne
    @JoinColumn(name = "organizador_codOrganizador")
    private Organizador organizador;

    @ManyToOne
    @JoinColumn(name = "endereco_codEndereco")
    private Endereco endereco;


    @OneToMany(mappedBy = "evento")
    private List<Kit> listaKits;


    @OneToMany(mappedBy = "evento")
    private List<Percurso> listaPercursos;


    @OneToMany(mappedBy = "evento")
    private List<Lote> listaLotes;

    @ManyToMany
    @JoinTable(
            name="Ranking_Evento",
            joinColumns=@JoinColumn(name="evento_codEvento"),
            inverseJoinColumns=@JoinColumn(name="ranking_codRanking")
    )
    private List<Ranking> listaRankings;

    public Evento() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Evento evento = (Evento) o;
        return Objects.equals(codEvento, evento.codEvento);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codEvento);
    }

}


