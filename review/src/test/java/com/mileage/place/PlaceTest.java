package com.mileage.place;

import org.axonframework.test.aggregate.AggregateTestFixture;
import org.axonframework.test.aggregate.FixtureConfiguration;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.UUID;

class PlaceTest {
    
    private final FixtureConfiguration<Place> testFixture = new AggregateTestFixture<>(Place.class);
    
    private static final String placeId = UUID.randomUUID().toString();
    
    @Test
    @DisplayName("test01")
    void test01() {
        testFixture.givenNoPriorActivity()
                   .when(new CreatePlaceCommand(placeId))
                   .expectSuccessfulHandlerExecution()
                   .expectEvents(new PlaceCreated(placeId));
    }
}