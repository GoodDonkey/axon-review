package com.mileage.core.events;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder@Data@AllArgsConstructor
public class ReviewIsFirstOnPlace {
    private String placeId;
    private String reviewId;
}
