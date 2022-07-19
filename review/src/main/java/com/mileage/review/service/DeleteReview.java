package com.mileage.review.service;

import com.mileage.review.web.ReviewRequestDTO;

import java.util.concurrent.CompletableFuture;

public interface DeleteReview {
    CompletableFuture<String> delete(ReviewRequestDTO dto);
}
