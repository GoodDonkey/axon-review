package com.mileage.review.web;

import com.mileage.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@RestController
@RequiredArgsConstructor
public class ReviewController{
    
    private final ReviewService reviewService;
    
    @PostMapping("/review/add")
    public ResponseEntity<String> add(@RequestBody ReviewRequestDTO dto) throws ExecutionException,
                                                                                InterruptedException,
                                                                                TimeoutException {
        String payload;
        payload = reviewService.addReview(dto).get(10, TimeUnit.SECONDS);
        return ResponseEntity.ok().body(payload);
    }
    
    @PutMapping("/review/modify")
    public ResponseEntity<String> modify(@RequestBody ReviewRequestDTO dto) throws ExecutionException,
                                                                                   InterruptedException,
                                                                                   TimeoutException {
        String payload;
        payload = reviewService.modify(dto).get(10, TimeUnit.SECONDS);
        return ResponseEntity.ok().body(payload);
    }
    
    @DeleteMapping("/review/delete")
    public ResponseEntity<String> delete(@RequestBody ReviewRequestDTO dto) throws ExecutionException,
                                                                                   InterruptedException,
                                                                                   TimeoutException {
        String payload;
        payload = reviewService.delete(dto).get(10, TimeUnit.SECONDS);
        return ResponseEntity.ok().body(payload);
    }
}
