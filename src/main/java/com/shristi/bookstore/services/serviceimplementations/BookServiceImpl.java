package com.shristi.bookstore.services.serviceimplementations;


import com.shristi.bookstore.dtos.BookRequest;
import com.shristi.bookstore.dtos.BookResponse;
import com.shristi.bookstore.dtos.BookUserResponse;
import com.shristi.bookstore.dtos.SignupResponse;
import com.shristi.bookstore.exceptions.InvalidRequestException;
import com.shristi.bookstore.exceptions.ResourceNotFoundException;
import com.shristi.bookstore.models.Author;
import com.shristi.bookstore.models.Book;
import com.shristi.bookstore.repositories.IAuthorRepository;
import com.shristi.bookstore.repositories.IBookRepository;
import com.shristi.bookstore.services.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class BookServiceImpl implements IBookService {

     @Autowired
     private IBookRepository bookRepository;
     @Autowired
     private IAuthorRepository authorRepository;
    @Override
    public BookResponse addBook(BookRequest bookRequest) {
        if(bookRepository.findByBookTitle(bookRequest.getBookTitle()).isPresent()) {
            throw new InvalidRequestException("Book already exists!: "+bookRequest.getBookTitle());
        }
        Book book1 =new Book();
        book1.setBookTitle(bookRequest.getBookTitle());
        book1.setBookDescription(bookRequest.getBookDescription());
        Author author=authorRepository.findById(bookRequest.getAuthorId()).orElseThrow(()-> new RuntimeException("No Author Details"));
        book1.setAuthor(author);
        bookRepository.save(book1);
        return new BookResponse(book1.getBookId(), book1.getBookTitle(),book1.getBookDescription());
    }
    @Override
    public BookResponse getBookById(Long bookId) {
        Book book=bookRepository.findById(bookId) .orElseThrow(() -> new ResourceNotFoundException("Book not found: "+bookId));
        return new BookResponse(book.getBookId(),book.getBookDescription(),book.getBookTitle());
    }
    @Override
    public List<BookResponse> getAllBooks() {
        return bookRepository.findAll()
                .stream()
                .map(books -> new BookResponse(books.getBookId(),books.getBookDescription(),books.getBookTitle())).collect(Collectors.toList());
    }

    @Override
    public String updateBookById(Long bookId, BookRequest bookRequest) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found: "+bookId));

        Author author = authorRepository.findById(bookRequest.getAuthorId())
                .orElseThrow(() -> new ResourceNotFoundException("Author not found: "+bookRequest.getAuthorId()));

        book.setBookTitle(bookRequest.getBookTitle());
        book.setBookDescription(bookRequest.getBookDescription());
        book.setAuthor(author);
        bookRepository.save(book);
        return "Updated bookId: " +bookId+ " details successfully";
    }

    @Override
    public String deleteBookById(Long bookId) {
        if (!bookRepository.existsById(bookId)) {
            throw new ResourceNotFoundException("Book not found: "+bookId);
        }
        bookRepository.deleteById(bookId);
        return "Deleted bookId: " +bookId+ " details successfully";
    }

    @Override
    public BookUserResponse getUsersByBookId(Long bookId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found: "+bookId));
        System.out.println(book);
        List<SignupResponse> userDetails = book.getUsers().stream()
                .map(user -> new SignupResponse(
                        user.getUserId(),
                        user.getUserName()              ,
                        user.getEmail()
                ))
                .toList();
        return new BookUserResponse(
                book.getBookId(),
                book.getBookTitle(),
                book.getBookDescription(),
                book.getAuthor().getAuthorId(),
                userDetails
        );
    }

}
