package com.example.BookstoreAPI.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> resourceNotFountExceptionHandler(ResourceNotFoundException ex){
        ApiResponse apiResponse = new ApiResponse(HttpStatus.NOT_FOUND.value(),ex.getMessage(),false);
        return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IdInUseException.class)
    public ResponseEntity<ApiResponse> idInUseExceptionHandler(IdInUseException ex){
        ApiResponse apiResponse=new ApiResponse(HttpStatus.BAD_REQUEST.value(), ex.getMessage(), false);
        return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse> handleValidationException(MethodArgumentNotValidException ex){
        String errors = ex.getAllErrors().toString();
        ApiResponse apiResponse= new ApiResponse(HttpStatus.BAD_REQUEST.value(), errors.substring(errors.lastIndexOf("default message")), false);
        return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.BAD_REQUEST);
    }

    // Handle generic exceptions
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse> handleGlobalException(Exception ex, WebRequest request) {
        ApiResponse apiResponse=new ApiResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage(), false);
        return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
