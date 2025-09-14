package com.bookstore.booksapplication.Services;

import com.bookstore.booksapplication.DTO.AuthorRequest;
import com.bookstore.booksapplication.DTO.AuthorResponse;

import java.util.List;

public interface AuthorService {
    AuthorResponse addAuthor(AuthorRequest request);
    List<AuthorResponse> getAllAuthors();
    void deleteAuthor(Long id);
    AuthorResponse updateAuthor(Long id, AuthorRequest request);
}
