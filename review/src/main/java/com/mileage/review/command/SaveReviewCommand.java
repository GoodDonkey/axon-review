package com.mileage.review.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@AllArgsConstructor
@Builder
public class SaveReviewCommand {
    @TargetAggregateIdentifier
    private String reviewId;
}
