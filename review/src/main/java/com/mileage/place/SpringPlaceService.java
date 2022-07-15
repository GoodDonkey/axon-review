package com.mileage.place;

import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class SpringPlaceService implements PlaceService{
    
    private final CommandGateway commandGateway;
    
    @Override
    public CompletableFuture<String> createPlace() {
        CreatePlaceCommand command = new CreatePlaceCommand(UUID.randomUUID().toString());
        return commandGateway.send(command);
    }
}
