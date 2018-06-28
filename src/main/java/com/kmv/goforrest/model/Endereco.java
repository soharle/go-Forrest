package com.kmv.goforrest.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Accessors(chain = true)
@Entity
@Getter
@Setter
@Table(name = "endereco")
public class Endereco implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long codEndereco;

    private String cep;
    private String logradouro;
    private String cidade;
    private String uf;
    private String numeroLogradouro;
    private String complementoLogradouro;
    private String bairro;

    @OneToMany(mappedBy = "endereco")
    private List<Usuario> listaUsuario;

    @OneToMany(mappedBy = "endereco")
    private List<LocalDeRetirada> listaLocalDeRetirada;

    @OneToMany(mappedBy = "endereco")
    private List<Evento> listaEvento;

    public Endereco() {

    }

}
