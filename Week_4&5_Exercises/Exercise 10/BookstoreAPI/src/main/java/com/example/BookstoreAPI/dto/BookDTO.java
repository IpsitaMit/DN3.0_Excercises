package com.example.BookstoreAPI.dto;

import org.springframework.stereotype.Component;

import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Component
public class BookDTO {
    @NotNull
    @Id
    private int id;
    @NotNull(message = "Title cannot be null")
    @Size(min = 1, max = 255, message = "Title must be between 1 and 255 characters")
    private String title;
    @NotNull(message = "Author cannot be null")
    @Size(min = 1, max = 255, message = "Author must be between 1 and 255 characters")
    private String author;
    @NotNull(message = "Price cannot be null")
    @Min(value = 0, message = "Price must be positive")
    private double price;
    @NotNull
    private String isbn;
}
