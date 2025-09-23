package com.shristi.bookstore.service.impl;

import com.shristi.bookstore.dto.*;
import com.shristi.bookstore.exception.InvalidRequestException;
import com.shristi.bookstore.exception.ResourceNotFoundException;
import com.shristi.bookstore.model.Book;
import com.shristi.bookstore.model.User;
import com.shristi.bookstore.repository.IBookRepository;
import com.shristi.bookstore.repository.IUserRepository;
import com.shristi.bookstore.service.declaration.IUserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements IUserService {

    private final IUserRepository userRepository;
    private final IBookRepository bookRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(IUserRepository userRepository, IBookRepository bookRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserResponse registerUser(UserRequest userRequest) {
        if (userRepository.findByEmail(userRequest.getEmail()).isPresent()) {
            throw new InvalidRequestException("User already exists: " + userRequest.getEmail());
        }
        User user1 = new User();
        user1.setUserName(userRequest.getUserName());
        user1.setEmail(userRequest.getEmail());
        user1.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        User user2 = userRepository.save(user1);
        return new UserResponse(user2.getUserId(), user2.getUserName());
    }

    @Override
    public UserResponse getUserById(Long userId) {
        User user1 = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found: " + userId));
        return new UserResponse(user1.getUserId(), user1.getUserName());
    }

    @Override
    public List<UserResponse> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(users -> new UserResponse(users.getUserId(), users.getUserName())).collect(Collectors.toList());
    }

    @Override
    public String updateUserById(Long userId, UserRequest userRequest) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found: " + userId));
        user.setUserName(userRequest.getUserName());
        user.setEmail(userRequest.getEmail());
        user.setPassword(userRequest.getPassword());
        userRepository.save(user);
        return "Updated" + userId + "details successfully";
    }

    @Override
    public String deleteUserById(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new ResourceNotFoundException("User not found: " + userId);
        }
        userRepository.deleteById(userId);
        return "Deleted" + userId + "details successfully";
    }

    @Override
    public String assignBooks(Long userId, List<Long> books) {
        User user1 = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found" + userId));
        List<Book> books1 = bookRepository.findAllById(books);
        user1.getBooks().addAll(books1);
        userRepository.save(user1);
        return books.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(", ", "Books assigned to User " + user1.getUserId() + " (" + user1.getUserName() + "): ", " successfully."));

    }

    @Override
    public String assignBookById(Long userId, Long bookId) {
        User user1 = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found: " + userId));
        Book book1 = bookRepository.findById(bookId).orElseThrow(() -> new ResourceNotFoundException("Book not found: " + bookId));
        user1.getBooks().add(book1);
        userRepository.save(user1);
        return "Book Assigned to " + user1.getUserId() + "(" + user1.getUserName() + "): " + bookId + "successfully.";


    }

    @Override
    public UserBookResponse getAssignedBooks(Long userId) {
        User user1 = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found: " + userId));
        System.out.println(user1);
        return new UserBookResponse(user1.getUserId(), user1.getUserName(), user1.getBooks().stream().map(book -> {
            BookResponse bookResponse = new BookResponse();
            bookResponse.setBookId(book.getBookId());
            bookResponse.setBookTitle(book.getBookTitle());
            bookResponse.setBookDescription(book.getBookDescription());
            bookResponse.setAuthorId(book.getAuthor().getAuthorId());
            return bookResponse;
        }).collect(Collectors.toList()));
    }

    @Override
    public String updateAssignedBooksById(Long userId, List<Long> books) {
        User user1 = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found: " + userId));
        List<Book> books1 = bookRepository.findAllById(books);
        Set<Book> newBooksSet = new HashSet<>(books1);
        user1.setBooks(newBooksSet);
        userRepository.save(user1);
        return "Updated User " + user1.getUserId() + "Books with new list successfully";
    }

    @Override
    public String deleteAssignedUserBooksById(Long userId) {
        User user1 = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found: " + userId));
        user1.getBooks().clear();
        userRepository.save(user1);
        return "Deleted User Assigned Books";
    }
}
