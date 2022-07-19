package com.mileage.place.web;

import com.mileage.place.service.PlaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
@RequiredArgsConstructor
public class PlaceController {
    private final PlaceService placeService;
    
    @PostMapping("/create_place")
    public CompletableFuture<String> createPlace() {
        return placeService.createPlace();
    }
}
