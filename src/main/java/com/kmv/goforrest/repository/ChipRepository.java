package com.kmv.goforrest.repository;

import com.kmv.goforrest.model.Chip;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChipRepository extends CrudRepository<Chip, Long> {
}
