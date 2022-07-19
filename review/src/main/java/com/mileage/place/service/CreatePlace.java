package com.mileage.place.service;

import java.util.concurrent.CompletableFuture;

public interface CreatePlace {
    CompletableFuture<String> createPlace();
}
