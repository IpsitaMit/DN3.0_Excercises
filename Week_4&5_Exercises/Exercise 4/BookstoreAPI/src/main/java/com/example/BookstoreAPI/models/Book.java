package com.example.BookstoreAPI.models;

import org.springframework.stereotype.Component;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class Book {

    private int id;
    private String title;
    private String author;
    private double price;
    private String isbn;

}
