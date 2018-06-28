package com.kmv.goforrest.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
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
public class Atleta extends Usuario implements Serializable {

    private String nomemerg;
    private String telEmerg;
    private String apelido;
    private float pace;


    @OneToMany(mappedBy = "atleta")
    private List<Pontuacao> listaPontuacoes;

    @OneToMany(mappedBy = "atleta")
    private List<Inscricao> listaInscricoes;



    public Atleta() {
    }
}
