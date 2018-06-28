package com.kmv.goforrest.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@Accessors(chain = true)
public class Pontuacao implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long codPontuacao;
    private float pontos;

    @ManyToOne
    @JoinColumn(name = "atleta_codUsuario")
    private Atleta atleta;

    @ManyToOne
    @JoinColumn(name = "ranking_codRanking")
    private Ranking ranking;



    public Pontuacao() {

    }

}
