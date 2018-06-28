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
public class LocalDeRetirada implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long codLocalDeRetirada;

    private String horaInicial;
    private String horaFinal;
    private String dataInicial;
    private String dataFinal;
    private String linkMapa;

    @OneToMany(mappedBy = "localDeRetirada")
    private List<Kit> listaKit;

    @ManyToOne
    @JoinColumn(name = "endereco_codEndereco")
    private Endereco endereco;



    public LocalDeRetirada() {

    }

}

