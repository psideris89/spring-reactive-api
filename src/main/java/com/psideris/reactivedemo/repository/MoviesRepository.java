package com.psideris.reactivedemo.repository;

import com.psideris.reactivedemo.model.Movie;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MoviesRepository extends ReactiveCrudRepository<Movie, String> {
}
