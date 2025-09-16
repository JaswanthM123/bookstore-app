package com.bookstore.booksapplication.Services;



import com.bookstore.booksapplication.DTO.SignupResponse;
import com.bookstore.booksapplication.DTO.SignupRequest;
import com.bookstore.booksapplication.DTO.UserResponse;

import java.util.List;


public interface UserService {
    SignupResponse registerUser(SignupRequest request);
    List<SignupResponse> getAllUsers();
    SignupResponse getUserById(Long userid);
    SignupResponse updateUser(Long id, SignupRequest request);
    void deleteUser(Long id);
    String AssignBook(Long id, List<Long> books);
    String AssignBookById(Long userid,Long bookid);
}
