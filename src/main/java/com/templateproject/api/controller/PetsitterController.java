package com.templateproject.api.controller;

import com.templateproject.api.dto.SearchRequest;
import com.templateproject.api.dto.SearchResponse;
import com.templateproject.api.dto.SpeciesResponse;
import com.templateproject.api.entity.Species;
import com.templateproject.api.entity.User;
import com.templateproject.api.repository.SpeciesRepository;
import com.templateproject.api.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@RestController
@RequestMapping("/api/petsitters")
public class PetsitterController {

    private final UserRepository userRepo;
    private  final SpeciesRepository speciesRepo;

    public PetsitterController(UserRepository userRepository, SpeciesRepository speciesRepo) {
        this.userRepo = userRepository;
        this.speciesRepo = speciesRepo;
    }

    @GetMapping("/{petsitterId}")
    public User getPetSitterById(@PathVariable UUID petsitterId) {
        return this.userRepo
                .findUserByIdAndIsPetSitter(petsitterId, true)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Le petsitter n'a pas été trouvé."));
    }

    @PostMapping("/search")
    public List<SearchResponse> getPetSitters(@RequestBody SearchRequest searchRequest) {
        if (searchRequest.getCity() == null ){
            searchRequest.setCity("Paris");
        }
        if (searchRequest.getTypeService() == null ){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Nous n'avons pas trouvé le type de service demandé.");
        }
        return this.userRepo
                .getPetSitterBySearch(searchRequest.getCity(), searchRequest.getTypeService(), searchRequest.getSpeciesList());
    }
}
