package com.shristi.bookstore.service.declaration;

import com.shristi.bookstore.dto.AuthorRequest;
import com.shristi.bookstore.dto.AuthorResponse;

import java.util.List;

public interface IAuthorService {
    AuthorResponse addAuthor(AuthorRequest authorRequest);

    List<AuthorResponse> getAllAuthors();

    String deleteAuthorById(Long authorId);

    String updateAuthorById(Long authorId, AuthorRequest authorRequest);

    AuthorResponse getAuthorById(Long authorId);
}
