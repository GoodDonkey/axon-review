package com.mileage.place.aggregate;

import com.mileage.core.events.place.ReviewAddedOnPlace;
import com.mileage.core.events.review.ReviewIsFirstOnPlace;
import com.mileage.core.events.place.PlaceCreated;
import com.mileage.core.events.review.ReviewIsNotFirstOnPlace;
import com.mileage.place.aggregate.command.AddReviewOnPlaceCommand;
import com.mileage.place.aggregate.command.CreatePlaceCommand;
import com.mileage.place.aggregate.command.CheckReviewIsFirstOnPlaceCommand;
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
            ReviewIsFirstOnPlace event = ReviewIsFirstOnPlace.builder().reviewId(reviewId).placeId(placeId).build();
            apply(event);
            log.debug("ReviewIsFirstOnPlace published: {}", event);
        } else {
            ReviewIsNotFirstOnPlace event = ReviewIsNotFirstOnPlace.builder().reviewId(reviewId).placeId(placeId)
                                                                   .build();
            apply(event);
            log.debug("ReviewIsNotFirstOnPlace published: {}", event);
        }
    }
    
    @CommandHandler
    public void handle(AddReviewOnPlaceCommand command) {
        log.debug("handling command: {}", command);
        String reviewId = command.getReviewId();
        String placeId = command.getPlaceId();
        ReviewAddedOnPlace event = ReviewAddedOnPlace.builder().reviewId(reviewId).placeId(placeId).build();
        apply(event);
    }
    
    @EventSourcingHandler
    public void on(ReviewAddedOnPlace event) {
        log.debug("event sourcing: {}", event);
        this.reviewIds.add(event.getReviewId());
    }
}
