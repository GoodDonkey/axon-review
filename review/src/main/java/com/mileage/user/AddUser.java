package com.mileage.user;

import java.util.concurrent.CompletableFuture;

public interface AddUser {
    CompletableFuture<String> addUser();
}
