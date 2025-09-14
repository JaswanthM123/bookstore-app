package com.bookstore.booksapplication.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SignupResponse {
    private Long userid;
    private String username;
    private String email;
}
