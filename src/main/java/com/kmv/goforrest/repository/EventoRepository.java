package com.kmv.goforrest.repository;

import com.kmv.goforrest.model.Evento;
import com.kmv.goforrest.model.Organizador;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventoRepository extends CrudRepository<Evento, Long> {
    Iterable<Evento> findAllByOrganizador(Organizador organizador);
}
