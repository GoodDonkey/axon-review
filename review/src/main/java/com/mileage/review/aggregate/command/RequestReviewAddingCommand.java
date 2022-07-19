package com.mileage.review.aggregate.command;

import lombok.*;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RequestReviewAddingCommand {
    @TargetAggregateIdentifier
    private String reviewId;
    private String content;
    private List<String> photoIds;
    private String placeId;
    private String userId;
}
