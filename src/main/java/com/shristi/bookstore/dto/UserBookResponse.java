package com.shristi.bookstore.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserBookResponse {
    private Long userId;
    private String userName;
    private List<BookResponse> books;
}
