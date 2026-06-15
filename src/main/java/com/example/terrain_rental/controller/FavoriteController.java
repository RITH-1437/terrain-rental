package com.example.terrain_rental.controller;

import com.example.terrain_rental.entity.Favorite;
import com.example.terrain_rental.repository.FavoriteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/favorites")
@RequiredArgsConstructor
public class FavoriteController {

    private final FavoriteRepository favoriteRepository;

    @GetMapping
    public List<Favorite> getAll() {
        return favoriteRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Favorite> getById(@PathVariable Long id) {
        return favoriteRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Favorite create(@RequestBody Favorite favorite) {
        return favoriteRepository.save(favorite);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Favorite> update(
            @PathVariable Long id,
            @RequestBody Favorite updatedFavorite) {

        return favoriteRepository.findById(id)
                .map(favorite -> {
                    favorite.setUserId(updatedFavorite.getUserId());
                    favorite.setTerrainId(updatedFavorite.getTerrainId());

                    return ResponseEntity.ok(
                            favoriteRepository.save(favorite)
                    );
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        if (!favoriteRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        favoriteRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}