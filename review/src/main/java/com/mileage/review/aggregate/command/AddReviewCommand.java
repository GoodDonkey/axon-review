package com.mileage.review.aggregate.command;

import lombok.*;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@EqualsAndHashCode
@Builder
public class AddReviewCommand {
    @TargetAggregateIdentifier
    private String reviewId;
    private String content;
    private List<String> photoIds;
    private String placeId;
    private String userId;
}
