package com.shristi.bookstore.controllers;

import com.shristi.bookstore.dtos.SignupResponse;
import com.shristi.bookstore.dtos.SignupRequest;
import com.shristi.bookstore.dtos.UserResponse;
import com.shristi.bookstore.services.serviceimplementations.UserServiceImpl;
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
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    /* Basic CRUD operations */
    @PostMapping
    public ResponseEntity<SignupResponse> signUp(@RequestBody SignupRequest signupRequest) {
     return ResponseEntity.ok(userService.registerUser(signupRequest));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<SignupResponse> getUserById(@PathVariable Long userId){
        return ResponseEntity.ok(userService.getUserById(userId));
    }

    @GetMapping
    public ResponseEntity<List<SignupResponse>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PutMapping("/{userId}")
    public ResponseEntity<String> updateUserById(@PathVariable Long userId, @RequestBody SignupRequest signupRequest) {
        userService.updateUserById(userId,signupRequest);
        return ResponseEntity.ok("User Details Updated successfully");
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteUserById(@PathVariable Long userId) {
        userService.deleteUserById(userId);
        return ResponseEntity.ok("User Deleted successfully");
    }


    /* More APIs for User */
    @PostMapping("/{userId}/books")
    public ResponseEntity<String> assignBookById(@PathVariable Long userId, @RequestBody List<Long> books){
         return ResponseEntity.ok(userService.assignBooks(userId,books));
    }

    @PostMapping("/{userId}/books/{bookId}")
    public ResponseEntity<String> assignBookByBookId(@PathVariable Long userId, @PathVariable Long bookId){
        return ResponseEntity.ok(userService.assignBookById(userId,bookId));
    }

    @GetMapping("/{userId}/books")
    public ResponseEntity<UserResponse> getAssignedBooks(@PathVariable Long userId){
        return ResponseEntity.ok(userService.getAssignedBooks(userId));
    }

    @PutMapping("/{userId}/books")
    public ResponseEntity<String> updateAssignedBooksById(@PathVariable Long userId,@RequestBody List<Long> books){
        return ResponseEntity.ok(userService.updateAssignedBooksById(userId,books));
    }

    @DeleteMapping("/{userId}/books")
    public ResponseEntity<String> deleteAssignedUserBookById(@PathVariable Long userId){
        return ResponseEntity.ok(userService.deleteAssignedUserBooksById(userId));

    }
}
