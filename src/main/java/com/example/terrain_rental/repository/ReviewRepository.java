package com.example.terrain_rental.repository;

import com.example.terrain_rental.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}