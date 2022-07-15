package com.mileage.place;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Slf4j
public class Place {
    @AggregateIdentifier
    private String placeId;
    
    @CommandHandler
    public Place(CreatePlaceCommand command) {
        log.debug("handling command: {}", command);
        AggregateLifecycle.apply(new PlaceCreated(command.getPlaceId()));
    }
    
    @EventSourcingHandler
    public void on(PlaceCreated event) {
        log.debug("event: {}", event);
        this.placeId = event.getPlaceId();
    }
}
