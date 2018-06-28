package com.kmv.goforrest.repository;

import com.kmv.goforrest.model.Percurso;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PercursoRepository extends CrudRepository<Percurso, Long> {
    Iterable<Percurso> getPercursosByEvento_CodEvento(Long codEvento);
}
