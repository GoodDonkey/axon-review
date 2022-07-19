package com.mileage.user.service;

import com.mileage.user.aggregate.command.CreateUserCommand;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class SpringUserService implements UserService {
    
    private final CommandGateway commandGateway;
    
    @Override
    public CompletableFuture<String> addUser() {
        CreateUserCommand command = new CreateUserCommand(UUID.randomUUID().toString());
        return commandGateway.send(command);
    }
}
