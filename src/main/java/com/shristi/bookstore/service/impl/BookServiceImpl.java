package com.shristi.bookstore.service.impl;


import com.shristi.bookstore.dto.*;
import com.shristi.bookstore.exception.InvalidRequestException;
import com.shristi.bookstore.exception.ResourceNotFoundException;
import com.shristi.bookstore.model.Author;
import com.shristi.bookstore.model.Book;
import com.shristi.bookstore.repository.IAuthorRepository;
import com.shristi.bookstore.repository.IBookRepository;
import com.shristi.bookstore.service.declaration.IBookService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements IBookService {

    private final IBookRepository bookRepository;
    private final IAuthorRepository authorRepository;

    public BookServiceImpl(IBookRepository bookRepository, IAuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public BookResponse addBook(BookRequest bookRequest) {
        if (bookRepository.findByBookTitle(bookRequest.getBookTitle()).isPresent()) {
            throw new InvalidRequestException("Book already exists!: " + bookRequest.getBookTitle());
        }
        Book book1 = new Book();
        book1.setBookTitle(bookRequest.getBookTitle());
        book1.setBookDescription(bookRequest.getBookDescription());
        Author author = authorRepository.findById(bookRequest.getAuthorId()).orElseThrow(() -> new ResourceNotFoundException("No Author Details: " + bookRequest.getAuthorId()));
        book1.setAuthor(author);
        bookRepository.save(book1);
        return new BookResponse(book1.getBookId(), book1.getBookTitle(), book1.getBookDescription(), book1.getAuthor().getAuthorId());
    }

    @Override
    public BookResponse getBookById(Long bookId) {
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new ResourceNotFoundException("Book not found: " + bookId));
        return new BookResponse(book.getBookId(), book.getBookTitle(), book.getBookDescription(),  book.getAuthor().getAuthorId());
    }

    @Override
    public List<BookResponse> getAllBooks() {
        return bookRepository.findAll()
                .stream()
                .map(books -> new BookResponse(books.getBookId(), books.getBookTitle(),books.getBookDescription(), books.getAuthor().getAuthorId())).collect(Collectors.toList());
    }

    @Override
    public String updateBookById(Long bookId, BookRequest bookRequest) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found: " + bookId));

        Author author = authorRepository.findById(bookRequest.getAuthorId())
                .orElseThrow(() -> new ResourceNotFoundException("Author not found: " + bookRequest.getAuthorId()));

        book.setBookTitle(bookRequest.getBookTitle());
        book.setBookDescription(bookRequest.getBookDescription());
        book.setAuthor(author);
        bookRepository.save(book);
        return "Updated bookId: " + bookId + " details successfully";
    }

    @Override
    public String deleteBookById(Long bookId) {
        if (!bookRepository.existsById(bookId)) {
            throw new ResourceNotFoundException("Book not found: " + bookId);
        }
        bookRepository.deleteById(bookId);
        return "Deleted bookId: " + bookId + " details successfully";
    }

    @Override
    public BookUserResponse getUsersByBookId(Long bookId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found: " + bookId));
        System.out.println(book);
        List<UserResponse> userDetails = book.getUsers().stream()
                .map(user -> new UserResponse(
                        user.getUserId(),
                        user.getUserName()
                ))
                .collect(Collectors.toList());
        return new BookUserResponse(
                book.getBookId(),
                book.getBookTitle(),
                book.getBookDescription(),
                book.getAuthor().getAuthorId(),
                userDetails
        );
    }

}
