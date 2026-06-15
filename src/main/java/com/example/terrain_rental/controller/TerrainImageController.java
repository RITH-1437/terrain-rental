package com.example.terrain_rental.controller;

import com.example.terrain_rental.entity.TerrainImage;
import com.example.terrain_rental.repository.TerrainImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/terrain-images")
@RequiredArgsConstructor
public class TerrainImageController {

    private final TerrainImageRepository terrainImageRepository;

    // Get All Terrain Images
    @GetMapping
    public List<TerrainImage> getAll() {
        return terrainImageRepository.findAll();
    }

    // Get Terrain Image By Id
    @GetMapping("/{id}")
    public ResponseEntity<TerrainImage> getById(@PathVariable Long id) {
        return terrainImageRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Create Terrain Image
    @PostMapping
    public TerrainImage create(@RequestBody TerrainImage terrainImage) {
        return terrainImageRepository.save(terrainImage);
    }

    // Update Terrain Image
    @PutMapping("/{id}")
    public ResponseEntity<TerrainImage> update(
            @PathVariable Long id,
            @RequestBody TerrainImage updatedImage) {

        return terrainImageRepository.findById(id)
                .map(image -> {
                    image.setTerrainId(updatedImage.getTerrainId());
                    image.setImagePath(updatedImage.getImagePath());
                    image.setUploadedAt(updatedImage.getUploadedAt());

                    TerrainImage saved = terrainImageRepository.save(image);
                    return ResponseEntity.ok(saved);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // Delete Terrain Image
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        if (!terrainImageRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        terrainImageRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}