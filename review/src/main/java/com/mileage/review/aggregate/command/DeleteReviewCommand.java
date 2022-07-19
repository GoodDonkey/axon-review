package com.mileage.review.aggregate.command;

import lombok.*;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@EqualsAndHashCode
public class DeleteReviewCommand {
    @TargetAggregateIdentifier
    private String reviewId;
}
