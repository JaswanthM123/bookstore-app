package com.bookstore.booksapplication.DTO;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AuthorResponse {
    private Long authorid;
    private String name;
    private String bio;
}
