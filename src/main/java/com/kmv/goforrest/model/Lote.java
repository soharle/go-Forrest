package com.kmv.goforrest.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Accessors(chain = true)
@Getter
@Setter
public class Lote implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long codLote;

    private String dataInicial;
    private String dataFinal;
    private float valor;

    @ManyToOne
    @JoinColumn(name = "evento_codEvento")
    private Evento evento;

    public Lote() {

    }

}
