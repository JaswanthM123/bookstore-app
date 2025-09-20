package com.shristi.bookstore.services;


import com.shristi.bookstore.dtos.BookRequest;
import com.shristi.bookstore.dtos.BookResponse;
import com.shristi.bookstore.dtos.BookUserResponse;

import java.util.List;


public interface IBookService {

    BookResponse addBook(BookRequest bookRequest);
    List<BookResponse> getAllBooks();
    String updateBookById(Long bookId, BookRequest bookRequest);
    String deleteBookById(Long bookId);
    BookUserResponse getUsersByBookId(Long bookId);
    BookResponse getBookById(Long bookId);
}
