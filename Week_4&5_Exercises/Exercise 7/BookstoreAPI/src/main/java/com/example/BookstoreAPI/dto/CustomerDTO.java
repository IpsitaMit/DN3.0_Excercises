package com.example.BookstoreAPI.dto;


import org.springframework.stereotype.Component;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Component
public class CustomerDTO {
    @NotNull
    private int id;
    @NotNull
    private String name;
    @NotNull
    private String email;
    @NotNull
    private String password;
}
