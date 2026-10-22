package com.rittah.order.exception;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(OrderNotFoundException.class) ResponseEntity<ErrorResponse> nf(OrderNotFoundException e,HttpServletRequest r){
        return ResponseEntity.status(404).body(new ErrorResponse(LocalDateTime.now(),404,"ORDER_NOT_FOUND",e.getMessage(),r.getRequestURI()));}
    @ExceptionHandler(InvalidOrderStatusException.class) ResponseEntity<ErrorResponse> status(InvalidOrderStatusException e,HttpServletRequest r){
        return ResponseEntity.status(409).body(new ErrorResponse(LocalDateTime.now(),409,"INVALID_ORDER_STATUS",e.getMessage(),r.getRequestURI()));}
    @ExceptionHandler(BusinessException.class) ResponseEntity<ErrorResponse> biz(BusinessException e,HttpServletRequest r){
        return ResponseEntity.status(409).body(new ErrorResponse(LocalDateTime.now(),409,"BUSINESS_ERROR",e.getMessage(),r.getRequestURI()));}
    @ExceptionHandler(MethodArgumentNotValidException.class) ResponseEntity<ErrorResponse> val(MethodArgumentNotValidException e,HttpServletRequest r){
        String m=e.getBindingResult().getFieldErrors().stream().map(x->x.getField()+": "+x.getDefaultMessage()).collect(Collectors.joining("; "));
        return ResponseEntity.badRequest().body(new ErrorResponse(LocalDateTime.now(),400,"VALIDATION_ERROR",m,r.getRequestURI()));}
}
