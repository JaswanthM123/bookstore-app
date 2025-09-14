package com.bookstore.booksapplication.ServiceImplementaions;

import com.bookstore.booksapplication.DTO.AuthorRequest;
import com.bookstore.booksapplication.DTO.AuthorResponse;
import com.bookstore.booksapplication.Models.Author;
import com.bookstore.booksapplication.Repositories.AuthorRepository;
import com.bookstore.booksapplication.Services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    private AuthorRepository authorRepository;
    @Override
    public AuthorResponse addAuthor(AuthorRequest request) {
        Author author = new Author();
        author.setName(request.getName());
        author.setBio(request.getBio());

        Author savedAuthor = authorRepository.save(author);

        return new AuthorResponse(savedAuthor.getAuthorid(), savedAuthor.getName(), savedAuthor.getBio());
    }

    @Override
    public List<AuthorResponse> getAllAuthors() {
        return authorRepository.findAll()
                .stream()
                .map(a -> new AuthorResponse(a.getAuthorid(), a.getName(), a.getBio()))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteAuthor(Long id) {
        if (!authorRepository.existsById(id)) {
            throw new RuntimeException("Author not found");
        }
        authorRepository.deleteById(id);
    }

    @Override
    public AuthorResponse updateAuthor(Long id, AuthorRequest request) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Author not found"));

        author.setName(request.getName());
        author.setBio(request.getBio());

        Author updated = authorRepository.save(author);
        return new AuthorResponse(updated.getAuthorid(), updated.getName(), updated.getBio());
    }
}
