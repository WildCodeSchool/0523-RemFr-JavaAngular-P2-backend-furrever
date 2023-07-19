package com.templateproject.api.controller;

import com.templateproject.api.dto.SearchRequest;
import com.templateproject.api.dto.SearchResponse;
import com.templateproject.api.entity.Service;
import com.templateproject.api.entity.User;
import com.templateproject.api.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/petsitters")
public class PetsitterController {

    private final UserRepository userRepo;

    public PetsitterController(UserRepository userRepository) {
        this.userRepo = userRepository;
    }

    @GetMapping("/{petsitterId}")
    public User getPetSitterById(@PathVariable UUID petsitterId) {
        return this.userRepo
                .findUserByIdAndIsPetSitter(petsitterId, true)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Le petsitter n'a pas été trouvé."));
    }

    @PostMapping("/search")
    public List<SearchResponse> getPetSitters(@RequestBody SearchRequest searchRequest) {
        //TODO voir controller requête préparée recherche pet sitter
        return this.userRepo
                .getPetSitterBySearch(searchRequest.getCity(), searchRequest.getTypeService(), searchRequest.getSpeciesList() );
    }
}
