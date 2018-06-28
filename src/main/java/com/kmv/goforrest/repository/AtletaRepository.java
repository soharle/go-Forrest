package com.kmv.goforrest.repository;

import com.kmv.goforrest.model.Atleta;
import com.kmv.goforrest.model.Inscricao;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AtletaRepository extends CrudRepository<Atleta, Long> {
    boolean existsAtletaByEmailAndSenha(String var1, String var2);
    Atleta getAtletaByEmail(String var1);
}
