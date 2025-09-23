package com.shristi.bookstore.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class BookResponse {
    private Long bookId;
    private String bookTitle;
    private String bookDescription;
    private Long authorId;
}
