package com.mileage.core.events;

import lombok.*;

import java.util.List;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode
public class ReviewModified {
    private String reviewId;
    private String content;
    private List<String> photoIds;
}
