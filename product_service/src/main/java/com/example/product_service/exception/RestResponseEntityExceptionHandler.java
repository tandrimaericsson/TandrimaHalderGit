package com.example.product_service.exception;

import com.example.product_service.model.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ProductServiceCustomException.class)
    public ResponseEntity<ErrorResponse> handleProductServiceCustomException(ProductServiceCustomException exception){
        return new ResponseEntity<>(new ErrorResponse().builder()
                .message(exception.getMessage())
                .errorCode(exception.getErrorCode())
                .build(),exception.getStatusCode());
    }
}
