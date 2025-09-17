package com.bookstore.booksapplication.DTO;

import com.bookstore.booksapplication.Models.user;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BookUserResponse {
    private Long bookid;
    private String title;
    private String description;
    private Long authorId;
    private List<SignupResponse> users;
}
