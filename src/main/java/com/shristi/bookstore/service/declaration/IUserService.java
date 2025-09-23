package com.shristi.bookstore.service.declaration;


import com.shristi.bookstore.dto.UserResponse;
import com.shristi.bookstore.dto.UserRequest;
import com.shristi.bookstore.dto.UserBookResponse;

import java.util.List;


public interface IUserService {
    UserResponse registerUser(UserRequest userRequest);

    List<UserResponse> getAllUsers();

    UserResponse getUserById(Long userId);

    String updateUserById(Long userId, UserRequest userRequest);

    String deleteUserById(Long userId);

    String assignBooks(Long userId, List<Long> books);

    String assignBookById(Long userId, Long bookId);

    UserBookResponse getAssignedBooks(Long userId);

    String updateAssignedBooksById(Long userId, List<Long> books);

    String deleteAssignedUserBooksById(Long userId);
}
