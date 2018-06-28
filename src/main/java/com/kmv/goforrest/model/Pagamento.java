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
public class Pagamento implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long codPagamento;

    private float valorTotal;
    private boolean status;
    private String tipoPagamento;
    private String codigoBarra;

    @OneToOne(mappedBy = "pagamento")
    private Inscricao inscricao;

    public Pagamento() {

    }

}
