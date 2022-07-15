package com.mileage.core.events;

import lombok.*;

import java.util.List;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode
public class ReviewSaved {
    private String reviewId;
    private String content;
    private List<String> photoIds;
    private String placeId;
    private String userId;
}
