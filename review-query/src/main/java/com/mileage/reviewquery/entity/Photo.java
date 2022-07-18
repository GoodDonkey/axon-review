package com.mileage.reviewquery.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Photo {
    
    @Id
    @Column(name = "photo_id")
    private String photoId;
    
    @ManyToOne
    @JoinColumn
    private Review review;
    
    public Review getReview() { return review; }
    
    public Photo(String photoId) {
        this.photoId = photoId;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Photo)) return false;
        Photo photo = (Photo) o;
        return Objects.equals(photoId, photo.photoId);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(photoId);
    }
}
