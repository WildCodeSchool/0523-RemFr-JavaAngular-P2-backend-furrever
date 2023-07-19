package com.templateproject.api.controller;

import com.templateproject.api.dto.SpeciesResponse;
import com.templateproject.api.entity.Species;
import com.templateproject.api.repository.SpeciesRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.net.http.HttpClient;
import java.util.List;

@RestController
public class SpeciesController {
    private final SpeciesRepository SpeciesRepo;

    public SpeciesController(SpeciesRepository speciesRepository){
        this.SpeciesRepo = speciesRepository;
    }

    @GetMapping("/api/species")
    public List<SpeciesResponse> getSpiecesUnique() {
        return this.SpeciesRepo
                .findAllSpecies()
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "Pas d'espece trouvé"));
    }
}