package com.example.BookstoreAPI.exceptions;

import lombok.Getter;

@Getter
public class IdInUseException extends RuntimeException{
    private String message;
    public IdInUseException(){
        super("This ID is already in use.");
        this.message="This ID is already in use.";
    }
}
