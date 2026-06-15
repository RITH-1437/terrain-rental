package com.example.terrain_rental.controller;

import com.example.terrain_rental.entity.Terrain;
import com.example.terrain_rental.repository.TerrainRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/terrains")
@RequiredArgsConstructor
public class TerrainController {

    private final TerrainRepository repository;

    @GetMapping
    public List<Terrain> getAll() {
        return repository.findAll();
    }

    @PostMapping
    public Terrain create(@RequestBody Terrain terrain) {
        return repository.save(terrain);
    }
}