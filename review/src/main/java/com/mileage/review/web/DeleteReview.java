package com.mileage.review.web;

import java.util.concurrent.CompletableFuture;

public interface DeleteReview {
    CompletableFuture<String> delete(ReviewRequestDTO dto);
}
