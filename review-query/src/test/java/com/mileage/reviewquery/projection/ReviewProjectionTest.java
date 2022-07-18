package com.mileage.reviewquery.projection;

import com.mileage.core.events.ReviewSaved;
import com.mileage.reviewquery.entity.Place;
import com.mileage.reviewquery.entity.Review;
import com.mileage.reviewquery.entity.User;
import com.mileage.reviewquery.entity.repository.PlaceJpaRepository;
import com.mileage.reviewquery.entity.repository.ReviewJpaRepository;
import com.mileage.reviewquery.entity.repository.UserJpaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ReviewProjectionTest {
    
    @Autowired ReviewJpaRepository reviewJpaRepository;
    @Autowired UserJpaRepository userJpaRepository;
    @Autowired PlaceJpaRepository placeJpaRepository;
    
    @Autowired
    ReviewProjection reviewProjection;
    Instant timestamp = Instant.now();
    
    String reviewId = UUID.randomUUID().toString();
    String placeId = UUID.randomUUID().toString();
    String userId = UUID.randomUUID().toString();
    String photoId = UUID.randomUUID().toString();
    String photoId2 = UUID.randomUUID().toString();
    
    @BeforeEach
    void before() {
        userJpaRepository.save(new User(userId));
        placeJpaRepository.save(new Place(placeId));
    }
    
    @Test
    @DisplayName("ReviewSaved 이벤트가 발행되면 Review 객체가 저장된다.")
    void test01() {
        // given
        ReviewSaved event = ReviewSaved.builder().reviewId(reviewId).placeId(placeId).userId(userId)
                                       .photoIds(List.of(photoId, photoId2)).isFirstOnPlace(false).build();
        // when
        reviewProjection.on(event, timestamp);
        // then
        Review review = reviewJpaRepository.findById(reviewId)
                .orElse(null);
    
        assertThat(review).isNotNull();
        assertThat(review.getReviewId()).isEqualTo(reviewId);
    }
}