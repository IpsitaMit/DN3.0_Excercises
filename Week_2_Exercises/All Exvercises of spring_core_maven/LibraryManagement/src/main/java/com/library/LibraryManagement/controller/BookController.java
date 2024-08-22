package com.library.LibraryManagement.controller;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.library.LibraryManagement.model.Book;
import com.library.LibraryManagement.service.BookService;

@RestController
public class BookController {
    @Autowired
    BookService service;

    @GetMapping("/")
    public List<Book> getBooks(){
        return service.getBooks();
    }

    @GetMapping("/getBook/{id}")
    public Book getBookById(@PathVariable int id) {
        return service.getBookById(id);
    }

    @PostMapping("/addBook")
    public void addBook(@RequestBody Book book){
        service.addBook(book);
    }

    @PutMapping("/updateBook")
    public void updateBook(@RequestBody Book book){
        service.updateBook(book);
    }
    @DeleteMapping("/deleteBook/{id}")
    public void deleteBook(@PathVariable int id){
        service.deleteBook(id);
    }
}
