package com.kmv.goforrest.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.io.Serializable;

@Accessors(chain = true)
@Entity
@Getter
@Setter
@Table(name = "distribuicao_pontos")
public class DistribuicaoDePontos implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long codDistribuicaoDePontos;

    private int colocacao;
    private float pontos;

    @ManyToOne
    @JoinColumn(name = "ranking_codRanking")
    private Ranking ranking;

    public DistribuicaoDePontos() {

    }

}
