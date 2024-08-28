package com.example.BookstoreAPI.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.BookstoreAPI.models.Book;

@Repository
public interface BookRepos extends JpaRepository<Book, Integer>{

    @Query("select b from Book b where "+
    "lower(b.title) like lower(concat('%', :keyword, '%')) or "+
    "lower(b.author) like lower(concat('%', :keyword, '%'))")
    List<Book> searchByKeyword(String keyword);
}
