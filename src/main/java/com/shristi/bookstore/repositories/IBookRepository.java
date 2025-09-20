package com.shristi.bookstore.repositories;

import com.shristi.bookstore.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface IBookRepository extends JpaRepository<Book,Long> {
    Optional<Book> findByBookTitle(String title);
}
