package com.library.LibraryManagement.service;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.LibraryManagement.model.Book;
import com.library.LibraryManagement.repository.BookRepository;

@Service
public class BookService {
    @Autowired
    BookRepository repo;


    public List<Book> getBooks(){
        return repo.findAll();
    }

    public void addBook(Book book) {
        repo.save(book);
    }

    public void updateBook(Book book) {
        repo.save(book);
    }

    public void deleteBook(int id) {
        repo.deleteById(id);
    }

    public Book getBookById(int id) {
        return repo.findById(id).orElse(null);
    }
}
