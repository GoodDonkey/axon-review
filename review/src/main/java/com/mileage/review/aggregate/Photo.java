package com.mileage.review.aggregate;

import lombok.*;
import org.axonframework.modelling.command.EntityId;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@EqualsAndHashCode
@Getter
public class Photo {
    @EntityId
    private String photoId;
}
