package com.bookstore.booksapplication.Services;



import com.bookstore.booksapplication.DTO.SignupResponse;
import com.bookstore.booksapplication.DTO.signuprequest;

import java.util.List;


public interface UserService {
    SignupResponse registerUser(signuprequest request);
    List<SignupResponse> getAllUsers();
    SignupResponse getUserById(Long userid);
    SignupResponse updateUser(Long id, signuprequest request);
    void deleteUser(Long id);
}
