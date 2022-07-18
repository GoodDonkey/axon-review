package com.mileage.reviewquery.entity.repository;

import com.mileage.reviewquery.entity.Place;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaceJpaRepository extends JpaRepository<Place, String> {
}
