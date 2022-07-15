package com.mileage.review.aggregate;

import com.mileage.core.events.ReviewSaved;
import com.mileage.review.command.AddReviewCommand;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateMember;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@EqualsAndHashCode
@Slf4j
public class Review {
    
    @AggregateIdentifier
    private String reviewId;
    private String content;
    private String placeId;
    private String userId;
    
    @AggregateMember
    private List<Photo> photos = new ArrayList<>();
    
    @CommandHandler
    public Review(AddReviewCommand command) {
        handle(command);
    }
    
    public void handle(AddReviewCommand command) {
        log.debug("handling command: {}", command);
        apply(ReviewSaved.builder()
                         .reviewId(command.getReviewId())
                         .placeId(command.getPlaceId())
                         .content(command.getContent())
                         .photoIds(command.getPhotoIds())
                         .userId(command.getUserId())
                         .build());
    }
    
    @EventSourcingHandler
    public void on(ReviewSaved event) {
        log.debug("event: {}", event);
        this.reviewId = event.getReviewId();
        this.content = event.getContent();
        this.placeId = event.getPlaceId();
        this.userId = event.getUserId();
        this.photos = event.getPhotoIds().stream().map(Photo::new).collect(Collectors.toList());
    }
}
