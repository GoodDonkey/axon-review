package com.mileage.core.events;

import lombok.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReviewIsFirstOnPlace {
    private String placeId;
    private String reviewId;
}
