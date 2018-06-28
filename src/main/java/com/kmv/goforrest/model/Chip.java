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
@Table(name = "chip")
public class Chip implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long codChip;

    private String tipoChip;
    private float distancia;

    @OneToOne(mappedBy = "chip")
    private Inscricao inscricao;

    public Chip() {

    }
}
