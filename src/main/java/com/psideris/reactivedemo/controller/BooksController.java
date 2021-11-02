package com.psideris.reactivedemo.controller;

import com.psideris.reactivedemo.model.Book;
import com.psideris.reactivedemo.model.BookMovie;
import com.psideris.reactivedemo.model.Movie;
import com.psideris.reactivedemo.repository.BookRepository;
import com.psideris.reactivedemo.repository.MoviesRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/books")
public class BooksController {

    private final BookRepository bookRepository;
    private final MoviesRepository moviesRepository;

    public BooksController(BookRepository bookRepository, MoviesRepository moviesRepository) {
        this.bookRepository = bookRepository;
        this.moviesRepository = moviesRepository;
    }

    @GetMapping
    public Flux<Book> getBooks() {
        return bookRepository.findAll();
    }

    @GetMapping("/{id}")
    public Mono<Book> getBook(@PathVariable String id) {
        return bookRepository.findBookById(id);
    }

    @GetMapping("movies/{id}")
    public Mono<BookMovie> getCombinedData(@PathVariable String id) {
        Mono<Book> book = bookRepository.findBookById(id);
        Mono<Movie> movie = moviesRepository.findById(id);

        return Mono.zip(book, movie, BookMovie::new);
        // Same logic - zipWith
        // return book.zipWith(movie).map(tuple2 -> new BookMovie(tuple2.getT1(), tuple2.getT2()));
    }
}

