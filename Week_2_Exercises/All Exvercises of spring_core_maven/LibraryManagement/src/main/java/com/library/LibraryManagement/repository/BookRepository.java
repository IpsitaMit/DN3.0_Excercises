package com.library.LibraryManagement.repository;
import com.library.LibraryManagement.model.Book;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer>{

}
