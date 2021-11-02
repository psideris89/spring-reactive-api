package com.psideris.reactivedemo.service;

import com.psideris.reactivedemo.model.Movie;
import com.psideris.reactivedemo.repository.MoviesRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.stream.Collectors;

import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Service
public class MoviesService {

    private final MoviesRepository moviesRepository;

    public MoviesService(MoviesRepository moviesRepository) {
        this.moviesRepository = moviesRepository;
    }

    public Flux<Movie> findAll() {
        return moviesRepository.findAll();
    }

    public Mono<Movie> findById(String id) {
        return moviesRepository.findById(id);
    }
}
