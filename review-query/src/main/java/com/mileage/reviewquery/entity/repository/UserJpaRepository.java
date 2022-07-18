package com.mileage.reviewquery.entity.repository;

import com.mileage.reviewquery.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepository extends JpaRepository<User, String> {
}
