package com.kmv.goforrest.repository;

import com.kmv.goforrest.model.Pontuacao;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PontuacaoRepository extends CrudRepository<Pontuacao, Long> {
}
