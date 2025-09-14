package com.bookstore.booksapplication.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class signuprequest {
    private String username;
    private String email;
    private String password;
}
