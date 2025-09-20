package com.shristi.bookstore.dtos;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BookResponse {
    private Long bookId;
    private String BookTitle;
    private String BookDescription;
}
