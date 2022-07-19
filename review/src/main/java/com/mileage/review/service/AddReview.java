package com.mileage.review.service;

import com.mileage.review.web.ReviewRequestDTO;

import java.util.concurrent.CompletableFuture;

public interface AddReview {
    CompletableFuture<String> addReview(ReviewRequestDTO dto);
}
