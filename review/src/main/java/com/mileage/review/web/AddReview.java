package com.mileage.review.web;

import java.util.concurrent.CompletableFuture;

public interface AddReview {
    CompletableFuture<String> addReview(ReviewRequestDTO dto);
}
