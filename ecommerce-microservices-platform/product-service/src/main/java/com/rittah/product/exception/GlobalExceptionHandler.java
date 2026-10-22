package com.rittah.product.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ProductNotFoundException.class)
    ResponseEntity<ErrorResponse> notFound(ProductNotFoundException e, HttpServletRequest r) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(new ErrorResponse(LocalDateTime.now(),404,"PRODUCT_NOT_FOUND",e.getMessage(),r.getRequestURI()));
    }

    @ExceptionHandler(DuplicateProductException.class)
    ResponseEntity<ErrorResponse> duplicate(DuplicateProductException e, HttpServletRequest r) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
            .body(new ErrorResponse(LocalDateTime.now(),409,"DUPLICATE_PRODUCT",e.getMessage(),r.getRequestURI()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    ResponseEntity<ErrorResponse> validation(MethodArgumentNotValidException e, HttpServletRequest r) {
        String msg = e.getBindingResult().getFieldErrors().stream()
            .map(x -> x.getField()+": "+x.getDefaultMessage()).collect(Collectors.joining("; "));
        return ResponseEntity.badRequest()
            .body(new ErrorResponse(LocalDateTime.now(),400,"VALIDATION_ERROR",msg,r.getRequestURI()));
    }
}
