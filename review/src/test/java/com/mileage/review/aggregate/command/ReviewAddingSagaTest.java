package com.mileage.review.aggregate.command;

import com.mileage.core.events.review.ReviewAddingRequested;
import com.mileage.core.events.review.ReviewIsFirstOnPlace;
import com.mileage.core.events.place.PlaceCreated;
import com.mileage.place.aggregate.command.AddReviewOnPlaceCommand;
import com.mileage.review.aggregate.Review;
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.axonframework.test.aggregate.FixtureConfiguration;
import org.axonframework.test.saga.SagaTestFixture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

class ReviewAddingSagaTest {
    
    private final FixtureConfiguration<Review> testFixture = new AggregateTestFixture<>(Review.class);
    private final SagaTestFixture<ReviewAddingSaga> sagaTestFixture = new SagaTestFixture<>(ReviewAddingSaga.class);
    
    String reviewId;
    String content;
    String placeId;
    String userId;
    List<String> photoIds;
    
    @BeforeEach
    void before() {
        reviewId = UUID.randomUUID().toString();
        content = "some content";
        placeId = UUID.randomUUID().toString();
        userId = UUID.randomUUID().toString();
        photoIds = List.of(UUID.randomUUID().toString(), UUID.randomUUID().toString(), UUID.randomUUID().toString());
    }
    
    @Test
    @DisplayName("리뷰 저장 요청이 들어왔을 때," +
                 "Place 에서 첫 리뷰인지 검증을 하고 관련 이벤트를 발행하고," +
                 "리뷰가 저장된 이벤트가 발행되는지 확인")
    void test01() {
    
        sagaTestFixture.givenAggregate(placeId)
                       .published(new PlaceCreated(placeId))
                       .andThenAggregate(reviewId)
                       .published(new ReviewAddingRequested(reviewId, content, placeId, userId, photoIds))
                       .whenAggregate(placeId)
                       .publishes(new ReviewIsFirstOnPlace(placeId, reviewId))
                       .expectAssociationWith("placeId", placeId)
                       .expectDispatchedCommands(new SaveReviewCommand(reviewId, true),
                                                 new AddReviewOnPlaceCommand(placeId, reviewId));
    
        sagaTestFixture.givenAggregate(reviewId)
                       .published(new ReviewAddingRequested(reviewId, content, placeId, userId, photoIds))
                       .whenAggregate(placeId)
                       .publishes(new ReviewIsFirstOnPlace(placeId, reviewId))
                       .expectAssociationWith("placeId", placeId)
                       .expectDispatchedCommands(new SaveReviewCommand(reviewId, true),
                                                 new AddReviewOnPlaceCommand(placeId, reviewId));
    }
}