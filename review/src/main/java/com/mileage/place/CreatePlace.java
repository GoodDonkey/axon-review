package com.mileage.place;

import java.util.concurrent.CompletableFuture;

public interface CreatePlace {
    CompletableFuture<String> createPlace();
}
