package com.bookstore.booksapplication.Controllers;


import com.bookstore.booksapplication.DTO.SignupResponse;
import com.bookstore.booksapplication.DTO.signuprequest;
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

    @PostMapping("/signup")
    public ResponseEntity<SignupResponse> signup(@RequestBody signuprequest signuprequest) {
     SignupResponse newuser=userService.registerUser(signuprequest);
     return ResponseEntity.ok(newuser);

    }
    @GetMapping("/allusers")
    public ResponseEntity<List<SignupResponse>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }
    @PutMapping("/UpdateUserById/{id}")
    public ResponseEntity<SignupResponse> updateUser(@PathVariable Long id, @RequestBody signuprequest request) {
        return ResponseEntity.ok(userService.updateUser(id, request));
    }
    @DeleteMapping("/deleteUserById/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok("User deleted successfully");
    }
}
