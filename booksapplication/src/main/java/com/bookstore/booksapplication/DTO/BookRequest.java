package com.bookstore.booksapplication.DTO;


import com.bookstore.booksapplication.Models.Author;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookRequest {
    private String title;
    private String description;
    private Long authorid;
}
