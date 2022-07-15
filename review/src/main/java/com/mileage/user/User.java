package com.mileage.user;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Slf4j
public class User {
    @AggregateIdentifier
    private String userId;
    
    @CommandHandler
    public User(CreateUserCommand command) {
        log.debug("handling command: {}", command);
        apply(new UserCreated(command.getUserId()));
    }
    
    @EventSourcingHandler
    public void on(UserCreated event) {
        log.debug("event: {}", event);
        this.userId = event.getUserId();
    }
}
