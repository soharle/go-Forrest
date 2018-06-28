package com.kmv.goforrest.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
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
public class Organizador extends Usuario implements Serializable{


    @OneToMany(mappedBy = "organizador")
    private List<Evento> listaEventos;

    public Organizador(){

    }

}
