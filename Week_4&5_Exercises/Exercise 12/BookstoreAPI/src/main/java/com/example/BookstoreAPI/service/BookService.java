package com.example.BookstoreAPI.service;

import java.util.*;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.BookstoreAPI.Repo.BookRepos;
import com.example.BookstoreAPI.dto.BookDTO;
import com.example.BookstoreAPI.exceptions.IdInUseException;
import com.example.BookstoreAPI.exceptions.ResourceNotFoundException;
import com.example.BookstoreAPI.models.Book;

import java.util.stream.Collectors;

@Service
public class BookService {


    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    public BookRepos repo;

    public ResponseEntity<?> getAllBooks() {

        //Custom heading
        HttpHeaders headers = new HttpHeaders();
        headers.add("All Books", "All books");
        List<BookDTO> books = repo.findAll().stream()
        .map(book -> bookToDto(book))
        .collect(Collectors.toList());
        return new ResponseEntity<>(books,headers,HttpStatus.OK);
    }

    public ResponseEntity<?> getBookById(int id){
        //Custom heading
        HttpHeaders headers = new HttpHeaders();
        headers.add("Book Exists", "found");
        Book book=repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found"));
        return new ResponseEntity<>(bookToDto(book),headers,HttpStatus.OK);
    }

    @SuppressWarnings("unused")
    public ResponseEntity<?> addBook(BookDTO bookDTO){
        Book book=dtoToBook(bookDTO);
        //Custom heading
        HttpHeaders headers = new HttpHeaders();
        headers.add("Successful","Book added");
        if (repo.findById(bookDTO.getId()).toString()!="Optional.empty"){
            throw new IdInUseException();
        }
        repo.save(book);
        return new ResponseEntity<>(bookDTO,headers,HttpStatus.CREATED);
    }

    public ResponseEntity<?> updateBook(int id, BookDTO updatedBookDTO){
        Book updatedBook=dtoToBook(updatedBookDTO);
        //Custom heading
        HttpHeaders headers = new HttpHeaders();
        headers.add("Successful","Book updated");
        repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found"));

        repo.save(updatedBook);
        return new ResponseEntity<>(updatedBookDTO,headers,HttpStatus.ACCEPTED);
    }

    public ResponseEntity<?> deleteBook(int id){
        
        //Custom heading
        HttpHeaders headers = new HttpHeaders();
        headers.add("Successful","Book deleted");

        repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found"));

        repo.deleteById(id);

        return new ResponseEntity<>(headers,HttpStatus.NO_CONTENT);
    }

    public ResponseEntity<List<BookDTO>> searchBooks(String keyword){
        
        //Custom heading
        HttpHeaders headers = new HttpHeaders();
        headers.add("Successful","Search results");
        List<BookDTO> books= (repo.searchByKeyword(keyword).stream()
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
