package com.bookstore.booksapplication.Services;


import com.bookstore.booksapplication.DTO.BookRequest;
import com.bookstore.booksapplication.DTO.BookResponse;
import com.bookstore.booksapplication.DTO.BookUserResponse;
import org.springframework.stereotype.Service;

import java.util.List;


public interface BookService {

    BookResponse addBook(BookRequest request);
    List<BookResponse> getAllBooks();
    BookResponse updateBook(Long id, BookRequest request);
    void deleteBook(Long id);
    BookUserResponse GetUsersById(Long bookid);
}
