package com.shristi.bookstore.controllers;


import com.shristi.bookstore.dtos.BookRequest;
import com.shristi.bookstore.dtos.BookResponse;
import com.shristi.bookstore.dtos.BookUserResponse;
import com.shristi.bookstore.services.serviceimplementations.BookServiceImpl;
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
@RequestMapping("/api/books")
public class BookController {
    @Autowired
    private BookServiceImpl bookService;

    @PostMapping
    public ResponseEntity<BookResponse> addBook(@RequestBody BookRequest bookRequest) {
        BookResponse newBook=bookService.addBook(bookRequest);
        return ResponseEntity.ok(newBook);
    }
    @GetMapping
    public ResponseEntity<List<BookResponse>> getAllBooks() {
        return ResponseEntity.ok(bookService.getAllBooks());
    }
    @GetMapping("/{bookId}")
    public ResponseEntity<BookResponse> getBookById(@PathVariable Long bookId){
        return ResponseEntity.ok(bookService.getBookById(bookId));
    }
    @PutMapping("/{bookId}")
    public ResponseEntity<String> updateBookById(@PathVariable Long bookId, @RequestBody BookRequest bookRequest) {
        return ResponseEntity.ok(bookService.updateBookById(bookId, bookRequest));
    }
    @DeleteMapping("/{bookId}")
    public ResponseEntity<String> deleteBookById(@PathVariable Long bookId) {
        return ResponseEntity.ok(bookService.deleteBookById(bookId));
    }
    @GetMapping("/{bookId}/users")
    public  ResponseEntity<BookUserResponse> getUsersByBookId(@PathVariable Long bookId){
        return ResponseEntity.ok(bookService.getUsersByBookId(bookId));
    }
}
