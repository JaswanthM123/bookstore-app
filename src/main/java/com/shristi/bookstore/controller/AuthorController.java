package com.shristi.bookstore.controller;

import com.shristi.bookstore.dto.AuthorRequest;
import com.shristi.bookstore.dto.AuthorResponse;
import com.shristi.bookstore.service.declaration.IAuthorService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {

    private final IAuthorService authorService;

    public AuthorController(IAuthorService authorService) {
        this.authorService = authorService;
    }

    @PostMapping
    public ResponseEntity<AuthorResponse> addAuthor(@Valid @RequestBody AuthorRequest authorRequest) {
        return new ResponseEntity<>(authorService.addAuthor(authorRequest), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<AuthorResponse>> getAllAuthors() {
        return ResponseEntity.ok(authorService.getAllAuthors());
    }

    @GetMapping("/{authorId}")
    public ResponseEntity<AuthorResponse> getAuthorById(@PathVariable Long authorId) {
        return ResponseEntity.ok(authorService.getAuthorById(authorId));
    }

    @PutMapping("/{authorId}")
    public ResponseEntity<String> updateAuthorById(@PathVariable Long authorId,@Valid @RequestBody AuthorRequest authorRequest) {
        return ResponseEntity.ok(authorService.updateAuthorById(authorId, authorRequest));
    }

    @DeleteMapping("/{authorId}")
    public ResponseEntity<String> deleteAuthorById(@PathVariable Long authorId) {
        return ResponseEntity.ok(authorService.deleteAuthorById(authorId));
    }
}
