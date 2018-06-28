package com.kmv.goforrest.repository;

import com.kmv.goforrest.model.DistribuicaoDePontos;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DistribuicaoDePontosRepository extends CrudRepository<DistribuicaoDePontos, Long> {
}
