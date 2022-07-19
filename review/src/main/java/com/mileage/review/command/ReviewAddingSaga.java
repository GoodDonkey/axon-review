package com.mileage.review.command;

import com.mileage.core.events.ReviewAddingRequested;
import com.mileage.core.events.ReviewIsFirstOnPlace;
import com.mileage.core.events.ReviewSaved;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.modelling.saga.EndSaga;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.SagaLifecycle;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.spring.stereotype.Saga;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Saga
@Slf4j
public class ReviewAddingSaga {
    
    private String reviewId;
    private String placeId;
    private String userId;
    private String content;
    private List<String> photoIds;
    private boolean firstOnPlaceProcessed = false;
    private boolean reviewSaved = false;
    
    @Autowired private transient CommandGateway commandGateway;
    
    @StartSaga
    @SagaEventHandler(associationProperty = "reviewId")
    public void handle(ReviewAddingRequested event) {
        // Place, User 등에 커맨드를 발행한다.
        log.debug("Saga Started by event: {}", event);
        reviewId = event.getReviewId();
        placeId = event.getPlaceId();
        content = event.getContent();
        userId = event.getUserId();
        photoIds = event.getPhotoIds();
        SagaLifecycle.associateWith("placeId", placeId);
        // 체크할 사항이 여러가지라면 여러 커맨드를 동시에 발행할 것.
        CheckReviewIsFirstOnPlaceCommand command = CheckReviewIsFirstOnPlaceCommand.builder()
                                                                                   .reviewId(reviewId)
                                                                                   .placeId(placeId)
                                                                                   .build();
        commandGateway.send(command);
    }
    
    @SagaEventHandler(associationProperty = "placeId")
    public void on(ReviewIsFirstOnPlace event) {
        this.firstOnPlaceProcessed = true;
        log.debug("handling event: {}", event);
        String reviewId = event.getReviewId();
        if (!reviewSaved) {
            SaveReviewCommand command = SaveReviewCommand.builder().reviewId(reviewId).build();
            commandGateway.send(command);
        }
    }
    
    @EndSaga
    @SagaEventHandler(associationProperty = "reviewId")
    public void on(ReviewSaved event) {
        this.reviewSaved = true;
        log.debug("Saga ending by event: {}", event);
    }
}
