package com.bookstore.booksapplication.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private Long userid;
    private String username;
    private String email;
    private List<BookRequest> books;

}
