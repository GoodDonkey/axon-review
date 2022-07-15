package com.mileage.review.web;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@RestController
@RequiredArgsConstructor
public class ReviewController{
    
    private final ReviewService reviewService;
    
    @PostMapping("/events")
    public ResponseEntity<String> events(@RequestBody ReviewRequestDTO dto) throws ExecutionException,
                                                                                   InterruptedException,
                                                                                   TimeoutException {
        String payload;
        if (dto.getAction().equals("ADD")) {
            payload = reviewService.addReview(dto).get(10, TimeUnit.SECONDS);
        } else if (dto.getAction().equals("MOD")) {
            payload = reviewService.modify(dto).get(10, TimeUnit.SECONDS);
        } else if (dto.getAction().equals("DELETE")) {
            payload = reviewService.delete(dto).get(10, TimeUnit.SECONDS);
        } else {
            payload = "요청을 처리하지 못했습니다.";
        }
        return ResponseEntity.ok().body(payload);
    }
}
