package com.kmv.goforrest.repository;

import com.kmv.goforrest.model.Organizador;
import org.aspectj.weaver.ast.Or;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganizadorRepository extends CrudRepository<Organizador, Long> {
    boolean existsOrganizadorByEmailAndSenha(String var1, String var2);
    Organizador getOrganizadorByEmail(String var1);
}
