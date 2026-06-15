package com.example.terrain_rental.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "terrains")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Terrain {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long ownerId;
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    private String location;
    private BigDecimal areaSize;
    private BigDecimal pricePerDay;

    private LocalDateTime availableFrom;
    private LocalDateTime availableTo;

    private Boolean isAvailable = true;

    private Long mainImageId;
}