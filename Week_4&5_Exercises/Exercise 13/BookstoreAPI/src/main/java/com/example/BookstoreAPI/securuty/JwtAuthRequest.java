package com.example.BookstoreAPI.securuty;

import lombok.Data;

@Data
public class JwtAuthRequest {
    private String username;
    private String password;
}
