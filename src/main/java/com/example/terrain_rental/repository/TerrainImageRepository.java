package com.example.terrain_rental.repository;

import com.example.terrain_rental.entity.TerrainImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TerrainImageRepository extends JpaRepository<TerrainImage, Long> {
}