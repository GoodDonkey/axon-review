package com.mileage.place;

import lombok.*;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AddReviewOnPlaceCommand {
    @TargetAggregateIdentifier
    private String placeId;
    private String reviewId;
}
