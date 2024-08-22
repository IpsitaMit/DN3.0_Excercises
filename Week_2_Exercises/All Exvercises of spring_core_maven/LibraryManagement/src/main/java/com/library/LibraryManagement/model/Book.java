package com.library.LibraryManagement.model;

import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.math.BigDecimal;

@Data
@Entity
@AllArgsConstructor
@Component
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //generate id
    private int id;
    private String name;
    private BigDecimal price;

    public Book(){}
}
