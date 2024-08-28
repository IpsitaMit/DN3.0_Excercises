package com.example.BookstoreAPI.service;

import java.util.*;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.BookstoreAPI.dto.BookDTO;
import com.example.BookstoreAPI.exceptions.ResourceNotFoundException;
import com.example.BookstoreAPI.models.Book;

import java.util.stream.Collectors;

@Service
public class BookService {

    private List<Book> bookList=new ArrayList<>();

    @Autowired
    private ModelMapper modelMapper;

    // Sample data
    public BookService() {
        bookList.add(new Book(1, "Percy Jackson", "Rick Riordan", 399, "1234567890"));
        bookList.add(new Book(2, "Hunger Games", "Suzzane Collins", 499, "0987654321"));
    }

    public ResponseEntity<List<BookDTO>> getAllBooks() {

        //Custom heading
        HttpHeaders headers = new HttpHeaders();
        headers.add("All Books", "All books");
        List<BookDTO> books = bookList.stream()
        .map(bookList -> bookToDto(bookList))
        .collect(Collectors.toList());
        return new ResponseEntity<>(books,headers,HttpStatus.OK);
    }

    public ResponseEntity<BookDTO> getBookById(int id){
        //Custom heading
        HttpHeaders headers = new HttpHeaders();
        headers.add("Book Exists", "found");
        Book book=bookList.stream()
                .filter(b -> b.getId() == id)
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Book not found"));
        return new ResponseEntity<>(bookToDto(book),headers,HttpStatus.OK);
    }

    public ResponseEntity<Void> addBook(BookDTO bookDTO){
        Book book=dtoToBook(bookDTO);
        //Custom heading
        HttpHeaders headers = new HttpHeaders();
        headers.add("Successful","Book added");
        bookList.add(book);
        return new ResponseEntity<>(headers,HttpStatus.CREATED);
    }

    public ResponseEntity<Void> updateBook(int id, BookDTO updatedBookDTO){
        Book updatedBook=dtoToBook(updatedBookDTO);
        //Custom heading
        HttpHeaders headers = new HttpHeaders();
        headers.add("Successful","Book updated");
        Book book = bookList.stream()
                .filter(b -> b.getId() == id)
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Book not found"));

        book.setTitle(updatedBook.getTitle());
        book.setAuthor(updatedBook.getAuthor());
        book.setPrice(updatedBook.getPrice());
        book.setIsbn(updatedBook.getIsbn());
        return new ResponseEntity<>(headers,HttpStatus.ACCEPTED);
    }

    public ResponseEntity<Void> deleteBook(int id){
        
        //Custom heading
        HttpHeaders headers = new HttpHeaders();
        headers.add("Successful","Book deleted");
        Book book = bookList.stream()
                .filter(b -> b.getId() == id)
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Book not found"));

        bookList.remove(book);
        return new ResponseEntity<>(headers,HttpStatus.NO_CONTENT);
    }

    public ResponseEntity<List<BookDTO>> searchBooks(String keyword){
        
        //Custom heading
        HttpHeaders headers = new HttpHeaders();
        headers.add("Successful","Search results");
        List<BookDTO> books= (bookList.stream()
        .filter(b -> b.getTitle().toLowerCase().contains(keyword.toLowerCase()) || b.getAuthor().toLowerCase().contains(keyword.toLowerCase()))
        .map(bookList -> bookToDto(bookList))
        .collect(Collectors.toList()));
        return new ResponseEntity<>(books,headers,HttpStatus.OK);
    }

    //conver Book to BookDTO
    public BookDTO bookToDto(Book book){
        BookDTO bookDTO = modelMapper.map(book, BookDTO.class);
        return bookDTO;
    }

    //convert BookDTO to Book
    public Book dtoToBook(BookDTO bookDTO){
        Book book=modelMapper.map(bookDTO, Book.class);
        return book;
    }
}
