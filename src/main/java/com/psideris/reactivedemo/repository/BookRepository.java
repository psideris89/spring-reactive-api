package com.psideris.reactivedemo.repository;

import com.psideris.reactivedemo.model.Book;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Repository
public class BookRepository {
    private final List<Book> books = new ArrayList<>();

    public Mono<Book> findBookById(String id) {
        return Mono.just(books.stream().filter(s -> s.id().equals(id)).findFirst().orElseThrow(() -> new RuntimeException("Not found")));
    }

    public Flux<Book> findAll() {
        return Flux.fromStream(books.stream());
    }

    public void save(Book book) {
        books.add(book);
    }
}
