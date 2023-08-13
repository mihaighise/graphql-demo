package com.mihaighise.graphqldemo.repository;

import com.mihaighise.graphqldemo.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    @Query("select a from Author a left join fetch a.books")
    List<Author> getAuthorsAndBooks();
}
