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
public class Inscricao implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long codInscricao;

    private boolean statusRetirada;
    private String tamCamisa;
    private int numeroPeito;
    private float precoTotal;
    private String dataInscricao;


    @OneToOne
    @JoinColumn(name = "chip_codChip")
    private Chip chip;


    @OneToOne(optional = true)
    @JoinColumn(name = "pagamento_codPagamento")
    private Pagamento pagamento;

    @OneToOne
    @JoinColumn(name = "kit_codKit")
    private Kit kit;

    @ManyToOne
    @JoinColumn(name = "atleta_codUsuario")
    private Atleta atleta;

    @ManyToOne
    @JoinColumn(name = "percurso_codPercurso")
    private Percurso percurso;


    public Inscricao() {

    }

}


