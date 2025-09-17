package com.bookstore.booksapplication.Controllers;


import com.bookstore.booksapplication.DTO.SignupResponse;
import com.bookstore.booksapplication.DTO.SignupRequest;
import com.bookstore.booksapplication.DTO.UserResponse;
import com.bookstore.booksapplication.ServiceImplementaions.BookServiceImpl;
import com.bookstore.booksapplication.ServiceImplementaions.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private BookServiceImpl bookService;

    @PostMapping("/signup")
    public ResponseEntity<SignupResponse> signup(@RequestBody SignupRequest signuprequest) {
     SignupResponse newuser=userService.registerUser(signuprequest);
     return ResponseEntity.ok(newuser);

    }
    @PostMapping("user/{userid}/assignedbooks")
    public ResponseEntity<String> AssignBook(@PathVariable Long userid, @RequestBody List<Long> books){
         return ResponseEntity.ok(userService.AssignBook(userid,books));
    }
    @GetMapping("/getAssignedBooksById/{userid}")
    public ResponseEntity<UserResponse> getAssignedBooks(@PathVariable Long userid){
        System.out.println(userid);
        return ResponseEntity.ok(userService.getAssignedBooks(userid));
    }
    @PostMapping("user/{userid}/assignBookById/{bookid}")
    public ResponseEntity<String> AssignBook(@PathVariable Long userid, @PathVariable Long bookid){
        return ResponseEntity.ok(userService.AssignBookById(userid,bookid));
    }
    @GetMapping("/allusers")
    public ResponseEntity<List<SignupResponse>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }
    @PutMapping("/updateAssignedBooksById/{userid}")
    public ResponseEntity<String> updateAssignedBooksById(@PathVariable Long userid,@RequestBody List<Long> books){
        return ResponseEntity.ok(userService.UpdateAssignedBooksById(userid,books));
    }

    @PutMapping("/UpdateUserById/{id}")
    public ResponseEntity<SignupResponse> updateUser(@PathVariable Long id, @RequestBody SignupRequest request) {
        return ResponseEntity.ok(userService.updateUser(id, request));
    }
    @DeleteMapping("/deleteUserById/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok("User deleted successfully");
    }
    @DeleteMapping("/deleteAssignedUserAllBookById/{userid}")
    public ResponseEntity<String> deleteAssignedUserBookById(@PathVariable Long userid){
        return ResponseEntity.ok(userService.DeleteAssignedUserBooksById(userid));

    }
}
