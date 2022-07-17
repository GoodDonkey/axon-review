package com.mileage.core.events;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
public class ReviewAddingRequested {
    private String reviewId;
    private String content;
    private String placeId;
    private String userId;
    private List<String> photoIds;
}
