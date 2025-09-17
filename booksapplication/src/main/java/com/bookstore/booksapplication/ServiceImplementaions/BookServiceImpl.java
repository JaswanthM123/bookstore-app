package com.bookstore.booksapplication.ServiceImplementaions;

import com.bookstore.booksapplication.DTO.*;
import com.bookstore.booksapplication.Models.Author;
import com.bookstore.booksapplication.Models.Book;
import com.bookstore.booksapplication.Repositories.AuthorRepository;
import com.bookstore.booksapplication.Repositories.BookRepository;
import com.bookstore.booksapplication.Services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class BookServiceImpl implements BookService {

     @Autowired
     private BookRepository bookRepository;
     @Autowired
     private AuthorRepository authorRepository;
    @Override
    public BookResponse addBook(BookRequest request) {
        if(bookRepository.findByTitle(request.getTitle()).isPresent()) {
            throw new RuntimeException("Book already exists!");
        }
        Book book1 =new Book();
        book1.setTitle(request.getTitle());
        book1.setDescription(request.getDescription());
        Author author=authorRepository.findById(request.getAuthorid()).orElseThrow(()-> new RuntimeException("Null value"));
        book1.setAuthor(author);
        bookRepository.save(book1);
        return new BookResponse(book1.getBookid(), book1.getTitle(),book1.getDescription());
    }

    @Override
    public List<BookResponse> getAllBooks() {
        System.out.println("service invoked");
        return bookRepository.findAll().stream().map(a -> new BookResponse(a.getBookid(),a.getDescription(),a.getTitle())).collect(Collectors.toList());
    }

    @Override
    public BookResponse updateBook(Long id, BookRequest request) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        Author author = authorRepository.findById(request.getAuthorid())
                .orElseThrow(() -> new RuntimeException("Author not found"));

        book.setTitle(request.getTitle());
        book.setDescription(request.getDescription());
        book.setAuthor(author);

        Book updated = bookRepository.save(book);
        return new BookResponse(updated.getBookid(), updated.getTitle(), updated.getDescription());
    }

    @Override
    public void deleteBook(Long id) {
        if (!bookRepository.existsById(id)) {
            throw new RuntimeException("Book not found");
        }
        bookRepository.deleteById(id);
    }

    @Override
    public BookUserResponse GetUsersById(Long bookid) {
        Book book = bookRepository.findById(bookid)
                .orElseThrow(() -> new RuntimeException("Book not found"));
        System.out.println(book);
        List<SignupResponse> userDtos = book.getUsers().stream()
                .map(user -> new SignupResponse(
                        user.getUserid(),
                        user.getUsername(),
                        user.getEmail()
                ))
                .toList();
        return new BookUserResponse(
                book.getBookid(),
                book.getTitle(),
                book.getDescription(),
                book.getAuthor().getAuthorid(),
                userDtos
        );
    }
}
