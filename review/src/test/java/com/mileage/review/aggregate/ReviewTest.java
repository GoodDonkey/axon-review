package com.mileage.review.aggregate;

import com.mileage.core.events.review.ReviewAddingRequested;
import com.mileage.core.events.review.ReviewSaved;
import com.mileage.review.aggregate.command.AddReviewCommand;
import com.mileage.review.aggregate.command.SaveReviewCommand;
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.axonframework.test.aggregate.FixtureConfiguration;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

class ReviewTest {
    
    private final FixtureConfiguration<Review> testFixture = new AggregateTestFixture<>(Review.class);
    
    
    private static final String reviewId = UUID.randomUUID().toString();
    private static final String content = "some content";
    private static final String placeId = UUID.randomUUID().toString();
    private static final String userId = UUID.randomUUID().toString();
    private static final List<String> photoIds = List.of(UUID.randomUUID().toString(),
                                                         UUID.randomUUID().toString(),
                                                         UUID.randomUUID().toString());
    
    
    @Test
    @DisplayName("AddReviewCommand test")
    void test01() {
        testFixture.givenNoPriorActivity()
                   .when(new AddReviewCommand(reviewId, content, photoIds, placeId, userId))
                   .expectSuccessfulHandlerExecution()
                   .expectEvents(ReviewAddingRequested.builder()
                                                      .reviewId(reviewId)
                                                      .content(content)
                                                      .photoIds(photoIds)
                                                      .placeId( placeId)
                                                      .userId(userId).build());
    }
    
    @Test
    @DisplayName("test02")
    void test02() {
        ReviewAddingRequested reviewAddingRequested = ReviewAddingRequested.builder().reviewId(reviewId).content(content)
                                                           .placeId(placeId).userId(userId).photoIds(photoIds).build();
    
        SaveReviewCommand saveReviewCommand = SaveReviewCommand.builder().reviewId(reviewId).isFirstOnPlace(true).build();
    
        ReviewSaved reviewSaved = ReviewSaved.builder().reviewId(reviewId).userId(userId)
                                          .content(content).photoIds(photoIds).placeId(placeId).isFirstOnPlace(true).build();
        testFixture.given(reviewAddingRequested)
                   .when(saveReviewCommand)
                   .expectSuccessfulHandlerExecution()
                   .expectEvents(reviewSaved);
    }
}