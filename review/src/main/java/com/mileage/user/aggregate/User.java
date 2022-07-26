package com.mileage.user.aggregate;

import com.mileage.core.events.user.UserCreated;
import com.mileage.user.aggregate.command.CreateUserCommand;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import javax.persistence.Entity;
import javax.persistence.Id;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Slf4j
@Entity
public class User {
    @Id
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
