package com.mileage.reviewquery.projection;

import com.mileage.core.events.review.ReviewSaved;
import com.mileage.reviewquery.entity.Photo;
import com.mileage.reviewquery.entity.Place;
import com.mileage.reviewquery.entity.Review;
import com.mileage.reviewquery.entity.User;
import com.mileage.reviewquery.entity.repository.PlaceJpaRepository;
import com.mileage.reviewquery.entity.repository.ReviewJpaRepository;
import com.mileage.reviewquery.entity.repository.UserJpaRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.AllowReplay;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventhandling.Timestamp;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
@Slf4j
@ProcessingGroup("review")
public class ReviewProjection {
    
    private ReviewJpaRepository reviewJpaRepository;
    private UserJpaRepository userJpaRepository;
    private PlaceJpaRepository placeJpaRepository;
    
    @EventHandler
    @AllowReplay
    protected void on(ReviewSaved event, @Timestamp Instant timestamp) {
        log.debug("event: {}, timestamp: {}", event, timestamp);
        String reviewId = event.getReviewId();
        String content = event.getContent();
        String placeId = event.getPlaceId();
        String userId = event.getUserId();
        boolean firstOnPlace = event.isFirstOnPlace();
        List<Photo> photos = event.getPhotoIds().stream().map(Photo::new).collect(Collectors.toList());
    
        User user = userJpaRepository.findById(userId).orElseThrow();
        Place place = placeJpaRepository.findById(placeId).orElseThrow();
        Review review = Review.builder().reviewId(reviewId).content(content).photos(photos).user(user).place(place).isFirstOnPlace(firstOnPlace).build();
        reviewJpaRepository.save(review);
    }
}
