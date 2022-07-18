package com.mileage.reviewquery.entity.repository;

import com.mileage.reviewquery.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewJpaRepository extends JpaRepository<Review, String> {

}
