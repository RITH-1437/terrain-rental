package com.example.terrain_rental.controller;

import com.example.terrain_rental.entity.Payment;
import com.example.terrain_rental.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentRepository paymentRepository;

    @GetMapping
    public List<Payment> getAll() {
        return paymentRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Payment> getById(@PathVariable Long id) {
        return paymentRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Payment create(@RequestBody Payment payment) {
        return paymentRepository.save(payment);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Payment> update(
            @PathVariable Long id,
            @RequestBody Payment updatedPayment) {

        return paymentRepository.findById(id)
                .map(payment -> {
                    payment.setBookingId(updatedPayment.getBookingId());
                    payment.setPaymentMethod(updatedPayment.getPaymentMethod());
                    payment.setAmountPaid(updatedPayment.getAmountPaid());
                    payment.setPaymentDate(updatedPayment.getPaymentDate());
                    payment.setStatus(updatedPayment.getStatus());
                    payment.setTransactionId(updatedPayment.getTransactionId());

                    return ResponseEntity.ok(
                            paymentRepository.save(payment)
                    );
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        if (!paymentRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        paymentRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}