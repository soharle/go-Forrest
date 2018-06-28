package com.kmv.goforrest.model;

import javax.persistence.*;
import java.io.Serializable;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Entity
@Getter
@Setter
@Table(name = "administrador")
public class Administrador implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long codAdministrador;

    private String login;
    private String senha;

    public Administrador() {

    }

    public Administrador(Long codAdministrador, String login, String senha) {
        this.setCodAdministrador(codAdministrador)
                .setLogin(login)
                .setSenha(senha);

    }
}
