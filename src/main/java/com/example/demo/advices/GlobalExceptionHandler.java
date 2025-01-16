package com.example.demo.advices;

import com.example.demo.Exception.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class GlobalExceptionHandler {
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<String> handleProductNotFoundExceptionException() {
        return new ResponseEntity<>(
                "ProductNotFoundException from GlobalExceptionHandler",
                HttpStatus.BAD_REQUEST
        );
    }
}
