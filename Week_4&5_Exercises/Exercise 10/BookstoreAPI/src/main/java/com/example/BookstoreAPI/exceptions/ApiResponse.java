package com.example.BookstoreAPI.exceptions;

import lombok.Getter;

@Getter
public class ApiResponse {
    private int statusCode;
    private String message;
    private boolean success;
    public ApiResponse(int statusCode, String message, boolean success){
        this.statusCode=statusCode;
        this.message=message;
        this.success=success;
    }
}
