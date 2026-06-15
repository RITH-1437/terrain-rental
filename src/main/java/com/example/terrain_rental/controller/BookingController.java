package com.example.terrain_rental.controller;

import com.example.terrain_rental.entity.Booking;
import com.example.terrain_rental.repository.BookingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
@RequiredArgsConstructor
public class BookingController {

    private final BookingRepository bookingRepository;

    @GetMapping
    public List<Booking> getAll() {
        return bookingRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Booking> getById(@PathVariable Long id) {
        return bookingRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Booking create(@RequestBody Booking booking) {
        return bookingRepository.save(booking);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Booking> update(
            @PathVariable Long id,
            @RequestBody Booking updatedBooking) {

        return bookingRepository.findById(id)
                .map(booking -> {
                    booking.setTerrainId(updatedBooking.getTerrainId());
                    booking.setRenterId(updatedBooking.getRenterId());
                    booking.setStartDate(updatedBooking.getStartDate());
                    booking.setEndDate(updatedBooking.getEndDate());
                    booking.setTotalPrice(updatedBooking.getTotalPrice());
                    booking.setStatus(updatedBooking.getStatus());

                    return ResponseEntity.ok(
                            bookingRepository.save(booking)
                    );
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        if (!bookingRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        bookingRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}