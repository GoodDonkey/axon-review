package com.mileage.review.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

@Slf4j
@ControllerAdvice(assignableTypes = {ReviewController.class})
public class ReviewControllerAdvice {
    
    @ExceptionHandler(ExecutionException.class)
    public ResponseEntity<ExceptionMessage> on(ExecutionException exception) {
        log.error("exception: {}", exception.getMessage());
        return ResponseEntity.internalServerError().body(ExceptionMessage.of(exception));
    }
    
    @ExceptionHandler(InterruptedException.class)
    public ResponseEntity<ExceptionMessage> on(InterruptedException exception) {
        log.error("exception: {}", exception.getMessage());
        return ResponseEntity.internalServerError().body(ExceptionMessage.of(exception));
    }
    
    @ExceptionHandler(TimeoutException.class)
    public ResponseEntity<ExceptionMessage> on(TimeoutException exception) {
        log.error("exception: {}", exception.getMessage());
        return ResponseEntity.internalServerError().body(ExceptionMessage.of(exception));
    }
}
