package com.mileage.reviewquery.projection;

import com.mileage.core.events.user.UserCreated;
import com.mileage.reviewquery.entity.User;
import com.mileage.reviewquery.entity.repository.UserJpaRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.AllowReplay;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventhandling.Timestamp;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
@AllArgsConstructor
@Slf4j
@ProcessingGroup("user")
public class UserProjection {
    
    private UserJpaRepository userJpaRepository;
    
    @EventHandler
    @AllowReplay
    protected void on(UserCreated event, @Timestamp Instant timestamp) {
        log.debug("event: {}, timestamp: {}", event, timestamp);
        String userId = event.getUserId();
        User user = User.builder().userId(userId).build();
        userJpaRepository.save(user);
    }
}
