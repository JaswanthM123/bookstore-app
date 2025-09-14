package com.bookstore.booksapplication.Controllers;

import com.bookstore.booksapplication.DTO.AuthorRequest;
import com.bookstore.booksapplication.DTO.AuthorResponse;
import com.bookstore.booksapplication.ServiceImplementaions.AuthorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {

    @Autowired
    private AuthorServiceImpl authorService;

    @PostMapping("/addauthor")
    public ResponseEntity<AuthorResponse> addAuthor(@RequestBody AuthorRequest request) {
        return ResponseEntity.ok(authorService.addAuthor(request));
    }

    // Get all authors
    @GetMapping("/allauthors")
    public ResponseEntity<List<AuthorResponse>> getAllAuthors() {
        return ResponseEntity.ok(authorService.getAllAuthors());
    }
    @PutMapping("/updateAuthorById/{id}")
    public ResponseEntity<AuthorResponse> updateAuthor(@PathVariable Long id, @RequestBody AuthorRequest request) {
        return ResponseEntity.ok(authorService.updateAuthor(id, request));
    }
    @DeleteMapping("/deleteAuthorById/{id}")
    public ResponseEntity<String> deleteAuthor(@PathVariable Long id) {
        authorService.deleteAuthor(id);
        return ResponseEntity.ok("Author deleted successfully");
    }
}
