package com.kmv.goforrest.repository;

import com.kmv.goforrest.model.Atleta;
import com.kmv.goforrest.model.Inscricao;
import com.kmv.goforrest.model.Organizador;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface InscricaoRepository extends CrudRepository<Inscricao, Long> {
    Iterable<Inscricao> findAllByAtleta(Atleta atleta);


}
