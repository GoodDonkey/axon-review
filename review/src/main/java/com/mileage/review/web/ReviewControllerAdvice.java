package com.mileage.review.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

@Slf4j
@ControllerAdvice(assignableTypes = {ReviewController.class})
public class ReviewControllerAdvice {
    
    @ExceptionHandler(ExecutionException.class)
    public ResponseEntity<ErrorResponseBody> on(ExecutionException exception, HttpServletRequest request) {
        log.error("exception: {}", exception.getMessage());
        return ResponseEntity.internalServerError().body(ErrorResponseBody.of(exception, request.getRequestURI()));
    }
    
    @ExceptionHandler(InterruptedException.class)
    public ResponseEntity<ErrorResponseBody> on(InterruptedException exception, HttpServletRequest request) {
        log.error("exception: {}", exception.getMessage());
        return ResponseEntity.internalServerError().body(ErrorResponseBody.of(exception, request.getRequestURI()));
    }
    
    @ExceptionHandler(TimeoutException.class)
    public ResponseEntity<ErrorResponseBody> on(TimeoutException exception, HttpServletRequest request) {
        log.error("exception: {}", exception.getMessage());
        return ResponseEntity.internalServerError().body(ErrorResponseBody.of(exception, request.getRequestURI()));
    }
}
