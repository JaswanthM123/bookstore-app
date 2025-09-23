package com.shristi.bookstore.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BookUserResponse {
    private Long bookId;
    private String bookTitle;
    private String bookDescription;
    private Long authorId;
    private List<UserResponse> users;
}
