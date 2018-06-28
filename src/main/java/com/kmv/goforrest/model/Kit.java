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
public class Kit implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long codKit;
    private String descricao;
    private Boolean camisa;
    private String produtos;
    private float valorKit;

    @OneToOne(mappedBy = "kit")
    private Inscricao inscricao;

    @ManyToOne
    @JoinColumn(name = "evento_codEvento")
    private Evento evento;

    @ManyToOne
    @JoinColumn(name = "localDeRetirada_codLocalDeRetirada")
    private LocalDeRetirada localDeRetirada;

    public Kit() {

    }
}
