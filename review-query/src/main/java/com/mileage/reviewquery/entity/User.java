package com.mileage.reviewquery.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "mileage_user")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
@Setter
public class User {
    
    @Id
    @Column(name = "user_id")
    private String userId;
    
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Review> reviews = new ArrayList<>();
    
    public void addReview(Review review) {
        this.reviews.add(review);
    }
    
    public User(String userId) {
        this.userId = userId;
    }
}
