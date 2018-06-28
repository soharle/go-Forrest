package com.kmv.goforrest.repository;

import com.kmv.goforrest.model.Administrador;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdministradorRepository extends CrudRepository<Administrador, Long> {
    boolean existsByLoginAndSenha(String var1, String var2);
    Administrador getAdministradorByLogin(String var1);
}
