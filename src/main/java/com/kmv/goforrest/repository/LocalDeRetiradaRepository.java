package com.kmv.goforrest.repository;

import com.kmv.goforrest.model.LocalDeRetirada;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocalDeRetiradaRepository extends CrudRepository<LocalDeRetirada, Long> {
}
