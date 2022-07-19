package com.mileage.user;

import com.mileage.core.events.user.UserCreated;
import com.mileage.user.aggregate.User;
import com.mileage.user.aggregate.command.CreateUserCommand;
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.axonframework.test.aggregate.FixtureConfiguration;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.UUID;

class UserTest {
    
    private final FixtureConfiguration<User> testFixture = new AggregateTestFixture<>(User.class);
    
    private static final String userId = UUID.randomUUID().toString();
    
    @Test
    @DisplayName("CreateUserCommand test")
    void test01() {
        testFixture.givenNoPriorActivity()
                   .when(new CreateUserCommand(userId))
                .expectSuccessfulHandlerExecution()
                .expectEvents(new UserCreated(userId));
    }
}