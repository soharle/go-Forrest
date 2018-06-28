package com.kmv.goforrest.repository;

import com.kmv.goforrest.model.Lote;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoteRepository extends CrudRepository<Lote, Long> {
}
