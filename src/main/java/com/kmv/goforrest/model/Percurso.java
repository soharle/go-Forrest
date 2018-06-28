package com.kmv.goforrest.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Accessors(chain = true)
@Getter
@Setter
public class Percurso implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long codPercurso;

    private String nome;
    private float quilometragem;
    private String itinerario;

    @ManyToOne
    @JoinColumn(name = "evento_codEvento")
    private Evento evento;

    @OneToMany(mappedBy = "percurso")
    private List<Inscricao> listaIncricoes;

    public Percurso() {

    }

}
