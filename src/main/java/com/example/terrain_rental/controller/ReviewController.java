package com.example.terrain_rental.controller;

import com.example.terrain_rental.entity.Review;
import com.example.terrain_rental.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewRepository reviewRepository;

    @GetMapping
    public List<Review> getAll() {
        return reviewRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Review> getById(@PathVariable Long id) {
        return reviewRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Review create(@RequestBody Review review) {
        return reviewRepository.save(review);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Review> update(
            @PathVariable Long id,
            @RequestBody Review updatedReview) {

        return reviewRepository.findById(id)
                .map(review -> {
                    review.setTerrainId(updatedReview.getTerrainId());
                    review.setUserId(updatedReview.getUserId());
                    review.setRating(updatedReview.getRating());
                    review.setComment(updatedReview.getComment());

                    return ResponseEntity.ok(
                            reviewRepository.save(review)
                    );
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        if (!reviewRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        reviewRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}