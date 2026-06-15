package com.example.terrain_rental.repository;

import com.example.terrain_rental.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}