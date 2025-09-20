package com.shristi.bookstore.services;

import com.shristi.bookstore.dtos.AuthorRequest;
import com.shristi.bookstore.dtos.AuthorResponse;

import java.util.List;

public interface IAuthorService {
    AuthorResponse addAuthor(AuthorRequest authorRequest);
    List<AuthorResponse> getAllAuthors();
    String deleteAuthorById(Long authorId);
    String updateAuthorById(Long authorId, AuthorRequest authorRequest);
    AuthorResponse getAuthorById(Long authorId);
}
