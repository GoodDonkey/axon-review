package com.mileage.review.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
public class RequestReviewAddingCommand {
    @TargetAggregateIdentifier
    private String reviewId;
    private String content;
    private List<String> photoIds;
    private String placeId;
    private String userId;
}
