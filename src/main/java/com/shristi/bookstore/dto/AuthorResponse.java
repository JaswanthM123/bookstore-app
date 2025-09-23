package com.shristi.bookstore.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AuthorResponse {
    private Long authorId;
    private String authorName;
    private String authorBio;
}
