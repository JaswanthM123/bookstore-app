package com.shristi.bookstore.controllers;

import com.shristi.bookstore.dtos.AuthorRequest;
import com.shristi.bookstore.dtos.AuthorResponse;
import com.shristi.bookstore.services.serviceimplementations.AuthorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private AuthorServiceImpl authorService;

    @PostMapping
    public ResponseEntity<AuthorResponse>addAuthor(@RequestBody AuthorRequest authorRequest) {
        return ResponseEntity.ok(authorService.addAuthor(authorRequest));
    }

    @GetMapping
    public ResponseEntity<List<AuthorResponse>> getAllAuthors() {
        return ResponseEntity.ok(authorService.getAllAuthors());
    }

    @GetMapping("/{authorId}")
    public ResponseEntity<AuthorResponse> getAuthorById(@PathVariable Long authorId){
        return ResponseEntity.ok(authorService.getAuthorById(authorId));
    }

    @PutMapping("/{authorId}")
    public ResponseEntity<String> updateAuthorById(@PathVariable Long authorId, @RequestBody AuthorRequest authorRequest) {
        return ResponseEntity.ok(authorService.updateAuthorById(authorId, authorRequest));
    }

    @DeleteMapping("/{authorId}")
    public ResponseEntity<String> deleteAuthorById(@PathVariable Long authorId) {
        return ResponseEntity.ok(authorService.deleteAuthorById(authorId));
    }
}
