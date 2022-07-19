package com.mileage.place;

import com.mileage.core.events.ReviewIsFirstOnPlace;
import com.mileage.core.events.place.PlaceCreated;
import com.mileage.review.command.CheckReviewIsFirstOnPlaceCommand;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.ArrayList;
import java.util.List;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Slf4j
public class Place {
    @AggregateIdentifier
    private String placeId;
    private List<String> reviewIds = new ArrayList<>();
    
    @CommandHandler
    public Place(CreatePlaceCommand command) {
        log.debug("handling command: {}", command);
        apply(new PlaceCreated(command.getPlaceId()));
    }
    
    @EventSourcingHandler
    public void on(PlaceCreated event) {
        log.debug("event sourcing: {}", event);
        this.placeId = event.getPlaceId();
        this.reviewIds = new ArrayList<>();
    }
    
    @CommandHandler
    public void handle(CheckReviewIsFirstOnPlaceCommand command) {
        log.debug("command: {}", command);
        String reviewId = command.getReviewId();
        String placeId = command.getPlaceId();
        if (reviewIds.isEmpty()){
            apply(ReviewIsFirstOnPlace.builder()
                                      .reviewId(reviewId)
                                      .placeId(placeId)
                                      .build());
            log.debug("ReviewIsFirstOnPlace published: {}", placeId);
        }
    }
}
