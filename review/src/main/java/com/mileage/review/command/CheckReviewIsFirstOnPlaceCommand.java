package com.mileage.review.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@AllArgsConstructor
@Data
@Builder
public class CheckReviewIsFirstOnPlaceCommand {
    private String reviewId;
    @TargetAggregateIdentifier
    private String placeId;
}
