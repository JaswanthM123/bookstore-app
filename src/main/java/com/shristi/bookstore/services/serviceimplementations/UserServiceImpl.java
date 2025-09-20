package com.shristi.bookstore.services.serviceimplementations;

import com.shristi.bookstore.dtos.BookRequest;
import com.shristi.bookstore.dtos.SignupResponse;
import com.shristi.bookstore.dtos.SignupRequest;
import com.shristi.bookstore.dtos.UserResponse;
import com.shristi.bookstore.models.Book;
import com.shristi.bookstore.models.User;
import com.shristi.bookstore.repositories.IBookRepository;
import com.shristi.bookstore.repositories.IUserRepository;
import com.shristi.bookstore.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private IBookRepository bookRepository;

    @Override
    public SignupResponse registerUser(SignupRequest signupRequest) {
        if(userRepository.findByEmail(signupRequest.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists!");
        }
        User user1 = new User();
        user1.setUserName(signupRequest.getUserName());
        user1.setEmail(signupRequest.getEmail());
        user1.setPassword(signupRequest.getPassword());
        User user2=userRepository.save(user1);
        return new SignupResponse(user2.getUserId(),user2.getEmail(),user2.getUserName());
    }

    @Override
    public SignupResponse getUserById(Long userId) {
        User user1=userRepository.findById(userId) .orElseThrow(() -> new RuntimeException("User not found"));
        return new SignupResponse(user1.getUserId(),user1.getEmail(),user1.getUserName());
    }

    @Override
    public List<SignupResponse> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(users -> new SignupResponse(users.getUserId(),users.getEmail(),users.getUserName())).collect(Collectors.toList());
    }

    @Override
    public String updateUserById(Long userId, SignupRequest signupRequest) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.setUserName(signupRequest.getUserName());
        user.setEmail(signupRequest.getEmail());
        user.setPassword(signupRequest.getPassword());
       userRepository.save(user);
       return "Updated" +userId+ "details successfully";
    }

    @Override
    public String deleteUserById(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new RuntimeException("User not found");
        }
        userRepository.deleteById(userId);
        return "Deleted" +userId+ "details successfully";
    }

    @Override
    public String assignBooks(Long userId, List<Long> books) {
            User user1=userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        List<Book> books1 = bookRepository.findAllById(books);
        user1.getBooks().addAll(books1);
        userRepository.save(user1);
        return books.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(", ", "Books assigned to User " + user1.getUserId() + " (" + user1.getUserName() + "): ", " successfully."));

    }

    @Override
    public String assignBookById(Long userId,Long bookId) {
        User user1=userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Book book1 =bookRepository.findById(bookId).orElseThrow(()-> new RuntimeException("Book not found"));
        user1.getBooks().add(book1);
        userRepository.save(user1);
        return "Book Assigned to " +user1.getUserId()+ "(" + user1.getUserName() + "): "+bookId+ "successfully.";


    }

    @Override
    public UserResponse getAssignedBooks(Long userId) {
        User user1=userRepository.findById(userId).orElseThrow(()-> new RuntimeException("User not found"));
        System.out.println(user1);
        return new UserResponse(user1.getUserId(),user1.getUserName(),user1.getEmail(),user1.getBooks().stream().map(book -> {
            BookRequest dto = new BookRequest();
            dto.setBookTitle(book.getBookTitle());
            dto.setBookDescription(book.getBookDescription());
            dto.setAuthorId(book.getAuthor().getAuthorId());
            return dto;
        }).toList());
    }

    @Override
    public String updateAssignedBooksById(Long userId ,List<Long> books) {
        User user1=userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        List<Book> books1 = bookRepository.findAllById(books);
        Set<Book> newBooksSet = new HashSet<>(books1);
         user1.setBooks(newBooksSet);
        userRepository.save(user1);
        return "Updated User " +user1.getUserId()+ "Books with new list successfully";
    }

    @Override
    public String deleteAssignedUserBooksById(Long userId) {
        User user1=userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        user1.getBooks().clear();
        userRepository.save(user1);
        return "Deleted User Assigned Books";
    }
}
