package com.mileage.review.command;

import lombok.*;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@AllArgsConstructor
@Data
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CheckReviewIsFirstOnPlaceCommand {
    private String reviewId;
    @TargetAggregateIdentifier
    private String placeId;
}
