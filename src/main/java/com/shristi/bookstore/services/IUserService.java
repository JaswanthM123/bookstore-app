package com.shristi.bookstore.services;


import com.shristi.bookstore.dtos.SignupResponse;
import com.shristi.bookstore.dtos.SignupRequest;
import com.shristi.bookstore.dtos.UserResponse;

import java.util.List;


public interface IUserService {
    SignupResponse registerUser(SignupRequest signupRequest);
    List<SignupResponse> getAllUsers();
    SignupResponse getUserById(Long userId);
    String updateUserById(Long userId, SignupRequest signupRequest);
    String deleteUserById(Long userId);
    String assignBooks(Long userId, List<Long> books);
    String assignBookById(Long userId,Long bookId);
    UserResponse getAssignedBooks(Long userId);
    String updateAssignedBooksById(Long userId,List<Long> books);
    String deleteAssignedUserBooksById(Long userId);
}
