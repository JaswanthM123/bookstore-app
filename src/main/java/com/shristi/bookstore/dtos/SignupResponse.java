package com.shristi.bookstore.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SignupResponse {
    private Long userId;
    private String userName;
    private String email;
}
