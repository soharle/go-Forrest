package com.kmv.goforrest.repository;

import com.kmv.goforrest.model.Ranking;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RankingRepository extends CrudRepository<Ranking, Long> {
}
