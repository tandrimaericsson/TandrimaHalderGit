package com.example.product_service.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
public class ProductServiceCustomException extends RuntimeException{

    private String message;
    private String errorCode;
    private HttpStatus statusCode;

    public ProductServiceCustomException(String message, String errorCode, HttpStatus statusCode) {
        super(message);
        this.message = message;
        this.errorCode = errorCode;
        this.statusCode = statusCode;
    }
}
