package com.example.terrain_rental.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "terrain_images")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TerrainImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long terrainId;

    private String imagePath;

    private LocalDateTime uploadedAt = LocalDateTime.now();
}