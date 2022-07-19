package com.mileage.review.aggregate.command;

import lombok.*;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SaveReviewCommand {
    @TargetAggregateIdentifier
    private String reviewId;
    private boolean isFirstOnPlace;
}
