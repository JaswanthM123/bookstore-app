package com.bookstore.booksapplication.DTO;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BookResponse {
    private Long bookid;
    private String title;
    private String description;
}
