package com.mileage.user.service;

import java.util.concurrent.CompletableFuture;

public interface AddUser {
    CompletableFuture<String> addUser();
}
