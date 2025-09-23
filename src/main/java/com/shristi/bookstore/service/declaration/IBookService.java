package com.shristi.bookstore.service.declaration;


import com.shristi.bookstore.dto.BookRequest;
import com.shristi.bookstore.dto.BookResponse;
import com.shristi.bookstore.dto.BookUserResponse;

import java.util.List;


public interface IBookService {

    BookResponse addBook(BookRequest bookRequest);
    List<BookResponse> getAllBooks();
    String updateBookById(Long bookId, BookRequest bookRequest);
    String deleteBookById(Long bookId);
    BookUserResponse getUsersByBookId(Long bookId);
    BookResponse getBookById(Long bookId);
}
