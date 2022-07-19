package com.mileage.review.web;

import com.mileage.review.command.AddReviewCommand;
import com.mileage.review.command.DeleteReviewCommand;
import com.mileage.review.command.ModifyReviewCommand;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class SpringReviewService implements ReviewService{
    
    private final CommandGateway commandGateway;
    
    @Override
    public CompletableFuture<String> delete(ReviewRequestDTO dto) {
        DeleteReviewCommand command = new DeleteReviewCommand(dto.getReviewId());
        return commandGateway.send(command);
    }
    
    @Override
    public CompletableFuture<String> modify(ReviewRequestDTO dto) {
        ModifyReviewCommand command = new ModifyReviewCommand(dto.getReviewId(),
                                                              dto.getContent(),
                                                              dto.getAttachedPhotoIds());
        return commandGateway.send(command);
    }
    
    @Override
    public CompletableFuture<String> addReview(ReviewRequestDTO dto) {
        String reviewId = dto.getReviewId();
        String content = dto.getContent();
        String placeId = dto.getPlaceId();
        String userId = dto.getUserId();
        List<String> photoIds = dto.getAttachedPhotoIds();
        
        AddReviewCommand command = AddReviewCommand.builder()
                                                   .reviewId(reviewId)
                                                   .content(content)
                                                   .userId(userId)
                                                   .placeId(placeId)
                                                   .photoIds(photoIds).build();
        return commandGateway.send(command);
    }
}
