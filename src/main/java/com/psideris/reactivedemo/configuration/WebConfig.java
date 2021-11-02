package com.psideris.reactivedemo.configuration;

import com.psideris.reactivedemo.model.Book;
import com.psideris.reactivedemo.model.Movie;
import com.psideris.reactivedemo.repository.BookRepository;
import com.psideris.reactivedemo.repository.MoviesRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import javax.annotation.PostConstruct;
import java.util.concurrent.TimeUnit;

@Configuration
public class WebConfig {

    private final BookRepository bookRepository;

    public WebConfig(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Bean
    public WebClient client() {
        return WebClient.create("http://localhost:8080");
    }

    @Bean
    public CommandLineRunner initMovies(MoviesRepository moviesRepository) {
        return (args ->
                moviesRepository
                        .deleteAll()
                        .thenMany(
                                Flux.just(
                                        new Movie("1", "Saw 1", "1999"),
                                        new Movie("2", "Riddick", "2006"),
                                        new Movie("3", "Lion King", "1995"),
                                        new Movie("4", "Halloween", "2002")))
                        .flatMap(moviesRepository::save)
                        .subscribe());
    }

    @PostConstruct
    public void initBooks() {
        Runnable runnable =
                () -> {
                    for (int i = 0; i < 100000; i++) {
                        bookRepository.save(new Book(String.valueOf(i), "Traveller", "£20", "Panos"));
                        try {
                            TimeUnit.SECONDS.sleep(1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                };

        Thread t = new Thread(runnable);
        t.start();
    }

//    @PostConstruct
//    public void sub() {
//        Flux<Book> booksFlux = client()
//                .get()
//                .uri("/books")
//                .retrieve()
//                .bodyToFlux(Book.class);
//
//        booksFlux.subscribe(System.out::println);
//    }
}