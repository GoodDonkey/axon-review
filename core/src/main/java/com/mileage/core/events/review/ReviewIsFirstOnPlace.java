package com.mileage.core.events.review;

import lombok.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReviewIsFirstOnPlace {
    private String placeId;
    private String reviewId;
}
