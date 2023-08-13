package com.mihaighise.graphqldemo.controller;

import com.mihaighise.graphqldemo.entity.Author;
import com.mihaighise.graphqldemo.entity.Book;
import com.mihaighise.graphqldemo.repository.AuthorRepository;
import com.mihaighise.graphqldemo.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
public class AuthorController {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookRepository bookRepository;

    @QueryMapping
    Iterable<Author> authors() {
        return authorRepository.getAuthorsAndBooks();
    }

    @QueryMapping
    Author authorById(@Argument Long id) {
        return authorRepository.findById(id).orElseThrow(NullPointerException::new);
    }

    @MutationMapping
    Book addBook(@Argument BookInput book) {
        Author author = authorRepository.findById(book.authorId()).orElseThrow(() ->new IllegalArgumentException("Author not found"));
        Book b = new Book(null, book.title(), book.publisher(), author);
        return bookRepository.save(b);
    }

    record BookInput(String title, String publisher, Long authorId) {}
}
