package com.shristi.bookstore.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BookUserResponse {
    private Long bookId;
    private String BookTitle;
    private String BookDescription;
    private Long authorId;
    private List<SignupResponse> users;
}
