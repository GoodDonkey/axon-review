package com.mileage.reviewquery.projection;

import com.mileage.core.events.place.PlaceCreated;
import com.mileage.reviewquery.entity.Place;
import com.mileage.reviewquery.entity.repository.PlaceJpaRepository;
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
@ProcessingGroup("place")
public class PlaceProjection {
    
    private PlaceJpaRepository placeJpaRepository;
    
    @EventHandler
    @AllowReplay
    protected void on(PlaceCreated event, @Timestamp Instant timestamp){
        log.debug("event: {}, timestamp: {}", event, timestamp);
        String placeId = event.getPlaceId();
        Place place = Place.builder().placeId(placeId).build();
        placeJpaRepository.save(place);
    }
}
