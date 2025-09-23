package com.shristi.bookstore.controller;


import com.shristi.bookstore.dto.BookRequest;
import com.shristi.bookstore.dto.BookResponse;
import com.shristi.bookstore.dto.BookUserResponse;
import com.shristi.bookstore.service.declaration.IBookService;
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
@RequestMapping("/api/books")
public class BookController {

    private final IBookService bookService;

    public BookController(IBookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    public ResponseEntity<BookResponse> addBook(@Valid @RequestBody BookRequest bookRequest) {
        return new ResponseEntity<>(bookService.addBook(bookRequest), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<BookResponse>> getAllBooks() {
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    @GetMapping("/{bookId}")
    public ResponseEntity<BookResponse> getBookById(@PathVariable Long bookId) {
        return ResponseEntity.ok(bookService.getBookById(bookId));
    }

    @PutMapping("/{bookId}")
    public ResponseEntity<String> updateBookById(@PathVariable Long bookId,@Valid @RequestBody BookRequest bookRequest) {
        return ResponseEntity.ok(bookService.updateBookById(bookId, bookRequest));
    }

    @DeleteMapping("/{bookId}")
    public ResponseEntity<String> deleteBookById(@PathVariable Long bookId) {
        return ResponseEntity.ok(bookService.deleteBookById(bookId));
    }

    @GetMapping("/{bookId}/users")
    public ResponseEntity<BookUserResponse> getUsersByBookId(@PathVariable Long bookId) {
        return ResponseEntity.ok(bookService.getUsersByBookId(bookId));
    }
}
