package com.templateproject.api.controller;

import com.templateproject.api.entity.Species;
import com.templateproject.api.repository.SpeciesRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:4200/")
public class SpeciesController {
    private final SpeciesRepository SpeciesRepo;

    public SpeciesController(SpeciesRepository speciesRepository){
        this.SpeciesRepo = speciesRepository;
    }

    @GetMapping("/api/species")
    public List<Species> getSpiecesUnique() {
        return this.SpeciesRepo.findAll();
    }
}