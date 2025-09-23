package com.shristi.bookstore.repository;


import com.shristi.bookstore.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IAuthorRepository extends JpaRepository<Author, Long> {
    Optional<Author> findByAuthorName(String name);
}
