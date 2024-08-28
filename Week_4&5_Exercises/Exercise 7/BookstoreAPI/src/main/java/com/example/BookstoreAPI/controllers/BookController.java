package com.example.BookstoreAPI.controllers;

import com.example.BookstoreAPI.dto.BookDTO;
import com.example.BookstoreAPI.service.BookService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;


@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    public BookService service;

    @GetMapping
    // @ResponseStatus(HttpStatus.OK)       Customizing HTTP Response Status with @ResponseStatus
    public ResponseEntity<List<BookDTO>> getAllBooks() {
        return service.getAllBooks();
    }

    @GetMapping("/{id}")
    // @ResponseStatus(HttpStatus.OK)        Customizing HTTP Response Status with @ResponseStatus
    public ResponseEntity<BookDTO> getBookById(@PathVariable int id) {
        return service.getBookById(id);
    }

    @PostMapping
    // @ResponseStatus(HttpStatus.CREATED)      Customizing HTTP Response Status with @ResponseStatus
    public ResponseEntity<Void> addBook(@RequestBody BookDTO bookDTO) {
        return service.addBook(bookDTO);
    }

    @PutMapping("/{id}")
    // @ResponseStatus(HttpStatus.ACCEPTED)     Customizing HTTP Response Status with @ResponseStatus
    public ResponseEntity<Void> updateBook(@PathVariable int id, @RequestBody BookDTO updatedBookDTO) {
        return service.updateBook(id, updatedBookDTO);
    }

    @DeleteMapping("/{id}")
    // @ResponseStatus(HttpStatus.NO_CONTENT)       Customizing HTTP Response Status with @ResponseStatus
    public ResponseEntity<Void> deleteBook(@PathVariable int id) {
        return service.deleteBook(id);
    }

    @GetMapping("/search")
    // @ResponseStatus(HttpStatus.OK)       Customizing HTTP Response Status with @ResponseStatus
    public ResponseEntity<List<BookDTO>> searchBooks(@RequestParam(required = true) String keyword){
        return service.searchBooks(keyword);
    }
}