package com.example.terrain_rental.config;

import com.example.terrain_rental.entity.*;
import com.example.terrain_rental.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Configuration
@RequiredArgsConstructor
public class DataSeeder {

    private final TerrainRepository terrainRepository;
    private final TerrainImageRepository terrainImageRepository;
    private final BookingRepository bookingRepository;
    private final PaymentRepository paymentRepository;
    private final ReviewRepository reviewRepository;
    private final FavoriteRepository favoriteRepository;

    @Bean
    CommandLineRunner seedData() {
        return args -> {

            // Terrain
            if (terrainRepository.count() == 0) {

                Terrain terrain = new Terrain();

                terrain.setOwnerId(1L);
                terrain.setTitle("CCUN Football Field");
                terrain.setDescription("Professional football field");
                terrain.setLocation("Phnom Penh");
                terrain.setAreaSize(new BigDecimal("500"));
                terrain.setPricePerDay(new BigDecimal("100"));
                terrain.setAvailableFrom(LocalDateTime.now());
                terrain.setAvailableTo(LocalDateTime.now().plusDays(30));
                terrain.setIsAvailable(true);

                Terrain savedTerrain = terrainRepository.save(terrain);

                // Terrain Image
                TerrainImage image = new TerrainImage();
                image.setTerrainId(savedTerrain.getId());
                image.setImagePath("football-field.jpg");
                image.setUploadedAt(LocalDateTime.now());

                TerrainImage savedImage = terrainImageRepository.save(image);

                savedTerrain.setMainImageId(savedImage.getId());
                terrainRepository.save(savedTerrain);

                // Booking
                Booking booking = new Booking();
                booking.setTerrainId(savedTerrain.getId());
                booking.setRenterId(2L);
                booking.setStartDate(LocalDate.now());
                booking.setEndDate(LocalDate.now().plusDays(2));
                booking.setTotalPrice(new BigDecimal("200"));
                booking.setStatus(BookingStatus.APPROVED);

                Booking savedBooking = bookingRepository.save(booking);

                // Payment
                Payment payment = new Payment();
                payment.setBookingId(savedBooking.getId());
                payment.setPaymentMethod("ABA");
                payment.setAmountPaid(new BigDecimal("200"));
                payment.setPaymentDate(LocalDate.now());
                payment.setStatus(PaymentStatus.PAID);
                payment.setTransactionId("TXN001");

                paymentRepository.save(payment);

                // Review
                Review review = new Review();
                review.setTerrainId(savedTerrain.getId());
                review.setUserId(2L);
                review.setRating(5);
                review.setComment("Excellent football field!");

                reviewRepository.save(review);

                // Favorite
                Favorite favorite = new Favorite();
                favorite.setUserId(2L);
                favorite.setTerrainId(savedTerrain.getId());

                favoriteRepository.save(favorite);

                System.out.println("=== SAMPLE DATA SEEDED SUCCESSFULLY ===");
            }
        };
    }
}