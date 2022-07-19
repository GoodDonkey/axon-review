package com.mileage.user.web;

import com.mileage.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
@RequiredArgsConstructor
public class UserController {
    
    private final UserService userService;
    
    @PostMapping("/create_user")
    public CompletableFuture<String> createUser() {
        return userService.addUser();
    }
}
