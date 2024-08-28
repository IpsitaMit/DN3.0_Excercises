package com.example.BookstoreAPI.dto;

import org.springframework.stereotype.Component;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Component
public class BookDTO {
    @NotNull
    private int id;
    @NotNull
    private String title;
    @NotNull
    private String author;
    @NotNull
    private double price;
    @NotNull
    private String isbn;
}
