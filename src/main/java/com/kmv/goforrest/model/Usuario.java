package com.kmv.goforrest.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Accessors(chain = true)
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Usuario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long codUsuario;

    private String nome;
    private String cpf;
    private String rg;
    private String dataNascimento;
    private Boolean sexo;
    private String senha;
    private String email;
    private String telFixo;
    private String telCel;

    @ManyToOne
    @JoinColumn(name = "endereco_codEndereco")
    private Endereco endereco;

    public Usuario() {
    }
}
