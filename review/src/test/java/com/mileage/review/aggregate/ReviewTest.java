package com.mileage.review.aggregate;

import com.mileage.core.events.ReviewSaved;
import com.mileage.review.command.AddReviewCommand;
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
                   .expectEvents(new ReviewSaved(reviewId, content, photoIds, placeId, userId));
    }
}