package com.example.terrain_rental.repository;

import com.example.terrain_rental.entity.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoriteRepository extends JpaRepository<Favorite, Long> {
}