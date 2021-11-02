package com.psideris.reactivedemo.route;

import com.psideris.reactivedemo.model.Movie;
import com.psideris.reactivedemo.service.MoviesService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Configuration
public class MoviesRouter {

    @Bean
    public RouterFunction<ServerResponse> routerFunction(MoviesService moviesService) {
        return RouterFunctions
                .route(GET("/movies"),
                        req -> ok().body(moviesService.findAll(), Movie.class))
                .andRoute(GET("/movies/{id}"),
                        req -> ok().body(moviesService.findById(req.pathVariable("id")), Movie.class));
    }
}
