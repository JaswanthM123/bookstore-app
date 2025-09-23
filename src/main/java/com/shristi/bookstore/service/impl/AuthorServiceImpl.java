package com.shristi.bookstore.service.impl;

import com.shristi.bookstore.dto.AuthorRequest;
import com.shristi.bookstore.dto.AuthorResponse;
import com.shristi.bookstore.exception.InvalidRequestException;
import com.shristi.bookstore.exception.ResourceNotFoundException;
import com.shristi.bookstore.model.Author;
import com.shristi.bookstore.repository.IAuthorRepository;
import com.shristi.bookstore.service.declaration.IAuthorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorServiceImpl implements IAuthorService {

    private final IAuthorRepository authorRepository;

    public AuthorServiceImpl(IAuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public AuthorResponse addAuthor(AuthorRequest authorRequest) {
        if (authorRepository.findByAuthorName(authorRequest.getAuthorName()).isPresent()) {
            throw new InvalidRequestException("Author already exists!: " + authorRequest.getAuthorName());
        }
        Author author = new Author();
        author.setAuthorName(authorRequest.getAuthorName());
        author.setAuthorBio(authorRequest.getAuthorBio());
        Author savedAuthor = authorRepository.save(author);
        return new AuthorResponse(savedAuthor.getAuthorId(), savedAuthor.getAuthorName(), savedAuthor.getAuthorBio());
    }

    @Override
    public List<AuthorResponse> getAllAuthors() {
        return authorRepository.findAll()
                .stream()
                .map(authors -> new AuthorResponse(authors.getAuthorId(), authors.getAuthorName(), authors.getAuthorBio()))
                .collect(Collectors.toList());
    }

    @Override
    public String deleteAuthorById(Long authorId) {
        if (!authorRepository.existsById(authorId)) {
            throw new ResourceNotFoundException("Author not found: " + authorId);
        }
        authorRepository.deleteById(authorId);
        return "Deleted Author: " + authorId + " details successfully";
    }

    @Override
    public String updateAuthorById(Long authorId, AuthorRequest authorRequest) {
        Author author = authorRepository.findById(authorId)
                .orElseThrow(() -> new ResourceNotFoundException("Author not found: " + authorId));

        author.setAuthorName(authorRequest.getAuthorName());
        author.setAuthorBio(authorRequest.getAuthorBio());

        authorRepository.save(author);
        return "Updated Author: " + authorId + "details successfully";
    }

    @Override
    public AuthorResponse getAuthorById(Long authorId) {
        Author author = authorRepository.findById(authorId).orElseThrow(() -> new ResourceNotFoundException("Author not found: " + authorId));
        return new AuthorResponse(author.getAuthorId(), author.getAuthorName(), author.getAuthorBio());
    }
}
