package com.templateproject.api.controller;

import com.templateproject.api.entity.Species;
import com.templateproject.api.repository.SpeciesRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
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