package com.mihaighise.graphqldemo;

import com.mihaighise.graphqldemo.entity.Author;
import com.mihaighise.graphqldemo.entity.Book;
import com.mihaighise.graphqldemo.repository.AuthorRepository;
import com.mihaighise.graphqldemo.repository.BookRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class GraphqlDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(GraphqlDemoApplication.class, args);
	}

	@Bean
	ApplicationRunner applicationRunner(AuthorRepository authorRepository, BookRepository bookRepository) {
		return args -> {
			Author josh = authorRepository.save(new Author(null, "Josh Long", new ArrayList<>()));
			Author mark = authorRepository.save(new Author(null, "Mark Heckler", new ArrayList<>()));
			bookRepository.saveAll(List.of(
					new Book(null, "Book 1", "Interactive books", josh),
					new Book(null, "Book 2", "Los Angeles books", mark),
					new Book(null, "Book 3", "Miami books", josh)
			));
		};
	}

}
