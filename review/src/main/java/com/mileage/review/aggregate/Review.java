package com.mileage.review.aggregate;

import com.mileage.core.events.ReviewAddingRequested;
import com.mileage.core.events.ReviewIsFirstOnPlace;
import com.mileage.core.events.ReviewModified;
import com.mileage.core.events.ReviewSaved;
import com.mileage.review.command.*;
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
    private FirstOnPlaceReview isFirstOnPlace;
    private ReviewStatus reviewStatus;
    
    @AggregateMember
    private List<Photo> photos = new ArrayList<>();
    
    @CommandHandler
    public Review(AddReviewCommand command) {
        handle(command);
    }
    
    public void handle(AddReviewCommand command) {
        log.debug("handling command: {}", command);
        String reviewId = command.getReviewId();
        String content = command.getContent();
        String placeId = command.getPlaceId();
        String userId = command.getUserId();
        List<String> photoIds = command.getPhotoIds();
    
        ReviewAddingRequested event = ReviewAddingRequested.builder().reviewId(reviewId).content(content)
                                                           .placeId(placeId).userId(userId).photoIds(photoIds).build();
        apply(event);
    }
    
    @EventSourcingHandler
    public void on(ReviewAddingRequested event) {
        log.debug("event sourcing: {}", event);
        this.reviewId = event.getReviewId();
        this.placeId = event.getPlaceId();
        this.userId = event.getUserId();
        this.content = event.getContent();
        this.photos = event.getPhotoIds().stream().map(Photo::new).collect(Collectors.toList());
        this.isFirstOnPlace = FirstOnPlaceReview.Processing; // Saga 를 통해 업데이트된다.
        this.reviewStatus = ReviewStatus.Processing;
    }
    
    @CommandHandler
    public void handle(SaveReviewCommand command) {
        log.debug("handling command: {}", command);
        if (ReviewStatus.Processing.equals(this.reviewStatus)) {
            ReviewSaved event = ReviewSaved.builder().reviewId(command.getReviewId()).build();
            apply(event);
        } else {
            throw new IllegalStateException("처리되고 있는 리뷰가 아닙니다.");
        }
    }
    
    @EventSourcingHandler
    public void on(ReviewSaved event) {
        log.debug("event sourcing: {}", event);
        this.reviewStatus = ReviewStatus.Saved;
    }
    
    @EventSourcingHandler
    public void on(ReviewIsFirstOnPlace event) {
        if (!FirstOnPlaceReview.True.equals(this.isFirstOnPlace)) {
            this.isFirstOnPlace = FirstOnPlaceReview.True;
        } else {
            throw new IllegalStateException("이미 첫번째 리뷰 입니다.");
        }
    }
    
    @CommandHandler
    public void handle(ModifyReviewCommand command) {
        log.debug("handling command: {}", command);
        // 변경 내용에 따라 서로 다른 이벤트를 발행한다.
        apply(ReviewModified.builder()
                            .reviewId(command.getReviewId())
                            .content(command.getContent())
                            .photoIds(command.getPhotoIds()));
    }
    
    @CommandHandler
    public void handle(DeleteReviewCommand command) {
        log.debug("handling command: {}", command);
        // 리뷰를 지움
        // 리뷰의 내용에 따라 여러 이벤트를 발행한다.
    }
    
    @EventSourcingHandler
    public void on(ReviewModified event) {
        this.content = event.getContent();
        this.photos = event.getPhotoIds().stream().map(Photo::new).collect(Collectors.toList());
    }
    
    private enum ReviewStatus {
        Processing, Saved, Rejected
    }
    
    private enum FirstOnPlaceReview {
        Processing, True, False
    }
}
