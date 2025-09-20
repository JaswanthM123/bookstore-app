package com.shristi.bookstore.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthorRequest {
    private String authorName;
    private String authorBio;

}
