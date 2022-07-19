package com.mileage.core.events.place;

import lombok.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReviewAddedOnPlace {
    private String placeId;
    private String reviewId;
}
