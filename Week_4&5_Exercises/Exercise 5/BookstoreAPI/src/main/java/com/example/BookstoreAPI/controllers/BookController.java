package com.example.BookstoreAPI.controllers;

import com.example.BookstoreAPI.models.Book;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.http.HttpHeaders;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/books")
public class BookController {


    private List<Book> bookList = new ArrayList<>();

    // Sample data
    public BookController() {
        bookList.add(new Book(1, "Percy Jackson", "Rick Riordan", 399, "1234567890"));
        bookList.add(new Book(2, "Hunger Games", "Suzzane Collins", 499, "0987654321"));
    }

    @GetMapping
    // @ResponseStatus(HttpStatus.OK)       Customizing HTTP Response Status with @ResponseStatus
    public ResponseEntity<List<Book>> getAllBooks() {

        //Custom heading
        HttpHeaders headers = new HttpHeaders();
        headers.add("All Books", "All books");
        return new ResponseEntity<>(bookList,headers,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    // @ResponseStatus(HttpStatus.OK)        Customizing HTTP Response Status with @ResponseStatus
    public ResponseEntity<Book> getBookById(@PathVariable int id) {

        //Custom heading
        HttpHeaders headers = new HttpHeaders();
        headers.add("Book Exists", "found");
        return new ResponseEntity<>(bookList.stream()
                .filter(book -> book.getId() == id)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Book not found")),headers,HttpStatus.OK);
    }

    @PostMapping
    // @ResponseStatus(HttpStatus.CREATED)      Customizing HTTP Response Status with @ResponseStatus
    public ResponseEntity<Void> addBook(@RequestBody Book book) {

        //Custom heading
        HttpHeaders headers = new HttpHeaders();
        headers.add("Successful","Book added");
        bookList.add(book);
        return new ResponseEntity<>(headers,HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    // @ResponseStatus(HttpStatus.ACCEPTED)     Customizing HTTP Response Status with @ResponseStatus
    public ResponseEntity<Void> updateBook(@PathVariable int id, @RequestBody Book updatedBook) {

        //Custom heading
        HttpHeaders headers = new HttpHeaders();
        headers.add("Successful","Book updated");
        Book book = bookList.stream()
                .filter(b -> b.getId() == id)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Book not found"));

        book.setTitle(updatedBook.getTitle());
        book.setAuthor(updatedBook.getAuthor());
        book.setPrice(updatedBook.getPrice());
        book.setIsbn(updatedBook.getIsbn());
        return new ResponseEntity<>(headers,HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    // @ResponseStatus(HttpStatus.NO_CONTENT)       Customizing HTTP Response Status with @ResponseStatus
    public ResponseEntity<Void> deleteBook(@PathVariable int id) {

        //Custom heading
        HttpHeaders headers = new HttpHeaders();
        headers.add("Successful","Book deleted");
        Book book = bookList.stream()
                .filter(b -> b.getId() == id)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Book not found"));

        bookList.remove(book);
        return new ResponseEntity<>(headers,HttpStatus.NO_CONTENT);
    }

    @GetMapping("/search")
    // @ResponseStatus(HttpStatus.OK)       Customizing HTTP Response Status with @ResponseStatus
    public ResponseEntity<List<Book>> searchBooks(@RequestParam(required = true) String keyword){

        //Custom heading
        HttpHeaders headers = new HttpHeaders();
        headers.add("Successful","Search results");
        return new ResponseEntity<>(bookList.stream()
        .filter(b -> b.getTitle().toLowerCase().contains(keyword.toLowerCase()) || b.getAuthor().toLowerCase().contains(keyword.toLowerCase()))
        .collect(Collectors.toList()),headers,HttpStatus.OK);
        
    }
}