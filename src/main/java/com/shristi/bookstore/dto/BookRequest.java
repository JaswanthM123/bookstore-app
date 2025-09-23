package com.shristi.bookstore.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookRequest {
    private String bookTitle;
    private String bookDescription;
    private Long authorId;
}
