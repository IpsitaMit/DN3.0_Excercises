package com.example.BookstoreAPI.controllers;

import com.example.BookstoreAPI.ApiRequests.ApiRequestMetrics;
import com.example.BookstoreAPI.dto.BookDTO;
import com.example.BookstoreAPI.service.BookService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    public BookService service;

    @Autowired
    public ApiRequestMetrics apiRequestMetrics;

    @GetMapping
    // @ResponseStatus(HttpStatus.OK)       Customizing HTTP Response Status with @ResponseStatus
    public ResponseEntity<?> getAllBooks() {
        apiRequestMetrics.incrementRequestCount();
        ResponseEntity<?> responseEntity = service.getAllBooks();
        @SuppressWarnings("unchecked")
        List<BookDTO> book=(List<BookDTO>) responseEntity.getBody();

        @SuppressWarnings("null")
        List<EntityModel<BookDTO>> resource = book.stream()
        .map(b ->{ EntityModel<BookDTO> model=EntityModel.of(b,
            (WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(BookController.class).deleteBook(b.getId())).withSelfRel()),
            (WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(BookController.class).updateBook(b.getId(), b)).withRel("Update")),
            (WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(BookController.class).getBookById(b.getId())).withRel("Get by Id")));
            return model;})
        .collect(Collectors.toList());

        CollectionModel<EntityModel<BookDTO>> collectionModel = CollectionModel.of(resource);
        // collectionModel.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(BookController.class).getAllBooks()).withSelfRel());

        if (!book.isEmpty()){
            collectionModel.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(BookController.class).searchBooks(null)).withRel("Search"));
            collectionModel.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(BookController.class).getAllBooks()).withRel("Get all books"));
            collectionModel.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(BookController.class).addBook(null)).withRel("Add book"));
            
        }

        return new ResponseEntity<>(collectionModel,HttpStatus.OK);
    }
    @Operation(summary = "Get a book by its ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Found the book"),
        @ApiResponse(responseCode = "404", description = "Book not found")
    })
    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    // @ResponseStatus(HttpStatus.OK)        Customizing HTTP Response Status with @ResponseStatus
    public ResponseEntity<?> getBookById(@PathVariable int id) {
        apiRequestMetrics.incrementRequestCount();
        ResponseEntity<?> responseEntity = service.getBookById(id);
        BookDTO book = (BookDTO) responseEntity.getBody();

        @SuppressWarnings("null")
        EntityModel<BookDTO> resource = EntityModel.of(book);
        resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(BookController.class).getAllBooks()).withRel("Get all books"));
        resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(BookController.class).deleteBook(id)).withRel("Delete"));
        resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(BookController.class).updateBook(id, book)).withRel("Update"));
        resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(BookController.class).searchBooks(null)).withRel("Search"));
        resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(BookController.class).addBook(null)).withRel("Add book"));
        return new ResponseEntity<>(resource,HttpStatus.OK);
    }

    @Operation(summary = "Create a new book")
    @ApiResponse(responseCode = "201", description = "Book created")
    @SuppressWarnings("null")
    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}, 
    produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    // @ResponseStatus(HttpStatus.CREATED)      Customizing HTTP Response Status with @ResponseStatus
    public ResponseEntity<?> addBook(@Valid @RequestBody BookDTO bookDTO) {
        apiRequestMetrics.incrementRequestCount();
        ResponseEntity<?> responseEntity = service.addBook(bookDTO);
        BookDTO book = (BookDTO) responseEntity.getBody();

        
        EntityModel<BookDTO> resource = EntityModel.of(book);
        resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(BookController.class).getAllBooks()).withRel("Get all books"));
        resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(BookController.class).deleteBook(book.getId())).withRel("Delete"));
        resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(BookController.class).updateBook(book.getId(), book)).withRel("Update"));
        resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(BookController.class).searchBooks(null)).withRel("Search"));
        resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(BookController.class).addBook(null)).withRel("Add book"));
        return new ResponseEntity<>(resource,HttpStatus.OK);
    }

    @SuppressWarnings("null")
    @PutMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    // @ResponseStatus(HttpStatus.ACCEPTED)     Customizing HTTP Response Status with @ResponseStatus
    public ResponseEntity<?> updateBook(@PathVariable int id,@Valid @RequestBody BookDTO updatedBookDTO) {
        apiRequestMetrics.incrementRequestCount();
        ResponseEntity<?> responseEntity = service.updateBook(id, updatedBookDTO);
        BookDTO book = (BookDTO) responseEntity.getBody();

        EntityModel<BookDTO> resource = EntityModel.of(book);
        resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(BookController.class).getAllBooks()).withRel("Get all books"));
        resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(BookController.class).deleteBook(book.getId())).withRel("Delete"));
        resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(BookController.class).updateBook(book.getId(), book)).withRel("Update"));
        resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(BookController.class).searchBooks(null)).withRel("Search"));
        resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(BookController.class).addBook(null)).withRel("Add book"));
        return new ResponseEntity<>(resource,HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    // @ResponseStatus(HttpStatus.NO_CONTENT)       Customizing HTTP Response Status with @ResponseStatus
    public ResponseEntity<?> deleteBook(@PathVariable int id) {
        apiRequestMetrics.incrementRequestCount();
        return service.deleteBook(id);
    }

    @GetMapping(value = "/search", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    // @ResponseStatus(HttpStatus.OK)       Customizing HTTP Response Status with @ResponseStatus
    public ResponseEntity<List<BookDTO>> searchBooks(@RequestParam(value ="keyword",required = true) String keyword){
        apiRequestMetrics.incrementRequestCount();
        return service.searchBooks(keyword);
    }
}