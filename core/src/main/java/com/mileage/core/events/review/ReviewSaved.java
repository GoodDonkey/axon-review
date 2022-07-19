package com.mileage.core.events.review;

import lombok.*;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReviewSaved {
    private String reviewId;
    private String content;
    private String placeId;
    private String userId;
    private List<String> photoIds;
    private boolean isFirstOnPlace;
}
