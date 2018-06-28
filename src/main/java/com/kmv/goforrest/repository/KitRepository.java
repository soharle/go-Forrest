package com.kmv.goforrest.repository;

import com.kmv.goforrest.model.Kit;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KitRepository extends CrudRepository<Kit, Long> {
}
