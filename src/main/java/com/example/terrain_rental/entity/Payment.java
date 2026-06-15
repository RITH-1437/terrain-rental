package com.example.terrain_rental.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "payments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long bookingId;

    private String paymentMethod;

    private BigDecimal amountPaid;

    private LocalDate paymentDate;

    @Enumerated(EnumType.STRING)
    private PaymentStatus status = PaymentStatus.PAID;

    private String transactionId;
}