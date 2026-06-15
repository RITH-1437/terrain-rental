package com.example.terrain_rental.repository;

import com.example.terrain_rental.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Long> {
}