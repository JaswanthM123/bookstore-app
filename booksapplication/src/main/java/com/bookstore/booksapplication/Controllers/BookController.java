package com.bookstore.booksapplication.Controllers;


import com.bookstore.booksapplication.DTO.BookRequest;
import com.bookstore.booksapplication.DTO.BookResponse;
import com.bookstore.booksapplication.DTO.BookUserResponse;
import com.bookstore.booksapplication.ServiceImplementaions.BookServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {
    @Autowired
    private BookServiceImpl bookService;

    @PostMapping("/addbook")
    public ResponseEntity<BookResponse> addBook(@RequestBody BookRequest request) {
        BookResponse newBook=bookService.addBook(request);
        return ResponseEntity.ok(newBook);
    }
    @GetMapping("/allbooks")
    public ResponseEntity<List<BookResponse>> getAllBooks() {
        System.out.println("Controller entered");
        return ResponseEntity.ok(bookService.getAllBooks());
    }
    @GetMapping("/getAssignedUsersByBookId/{bookid}")
    public  ResponseEntity<BookUserResponse> getUsersByBookId(@PathVariable Long bookid){
        return ResponseEntity.ok(bookService.GetUsersById(bookid));
    }
    @PutMapping("/updateBookById/{id}")
    public ResponseEntity<BookResponse> updateBook(@PathVariable Long id, @RequestBody BookRequest request) {
        return ResponseEntity.ok(bookService.updateBook(id, request));
    }
    @DeleteMapping("/deleteBookById/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.ok("Book deleted successfully");
    }
}
