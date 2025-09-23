package com.shristi.bookstore.controller;

import com.shristi.bookstore.dto.UserResponse;
import com.shristi.bookstore.dto.UserRequest;
import com.shristi.bookstore.dto.UserBookResponse;
import com.shristi.bookstore.service.declaration.IUserService;
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
@RequestMapping("/api/users")
public class UserController {

    private final IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    /* Basic CRUD operations */
    @PostMapping
    public ResponseEntity<UserResponse> signUp(@Valid @RequestBody UserRequest userRequest) {
        return new ResponseEntity<>(userService.registerUser(userRequest), HttpStatus.CREATED);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long userId) {
        return ResponseEntity.ok(userService.getUserById(userId));
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PutMapping("/{userId}")
    public ResponseEntity<String> updateUserById(@PathVariable Long userId,@Valid @RequestBody UserRequest userRequest) {
        return ResponseEntity.ok(userService.updateUserById(userId, userRequest));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteUserById(@PathVariable Long userId) {
        return ResponseEntity.ok(userService.deleteUserById(userId));
    }


    /* More APIs for User */
    @PostMapping("/{userId}/books")
    public ResponseEntity<String> assignBookById(@PathVariable Long userId,@Valid @RequestBody List<Long> books) {
        return ResponseEntity.ok(userService.assignBooks(userId, books));
    }

    @PostMapping("/{userId}/books/{bookId}")
    public ResponseEntity<String> assignBookByBookId(@PathVariable Long userId, @PathVariable Long bookId) {
        return ResponseEntity.ok(userService.assignBookById(userId, bookId));
    }

    @GetMapping("/{userId}/books")
    public ResponseEntity<UserBookResponse> getAssignedBooks(@PathVariable Long userId) {
        return ResponseEntity.ok(userService.getAssignedBooks(userId));
    }

    @PutMapping("/{userId}/books")
    public ResponseEntity<String> updateAssignedBooksById(@PathVariable Long userId,@Valid @RequestBody List<Long> books) {
        return ResponseEntity.ok(userService.updateAssignedBooksById(userId, books));
    }

    @DeleteMapping("/{userId}/books")
    public ResponseEntity<String> deleteAssignedUserBookById(@PathVariable Long userId) {
        return ResponseEntity.ok(userService.deleteAssignedUserBooksById(userId));

    }
}
