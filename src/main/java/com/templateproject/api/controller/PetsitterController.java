package com.templateproject.api.controller;

import com.templateproject.api.entity.Service;
import com.templateproject.api.entity.User;
import com.templateproject.api.repository.ServiceRepository;
import com.templateproject.api.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/petsitters")
public class PetsitterController {

    private final UserRepository userRepo;

    public PetsitterController(UserRepository userRepository){
        this.userRepo = userRepository;
    }

    @GetMapping("/{id}")
    public User getPetSitterById(@PathVariable UUID id){
            return this.userRepo
                    .findUserByIdAndIsPetSitter(id, true)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public User updatePetsitter(@PathVariable UUID id, @RequestBody User userToModify){
        this.userRepo
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return this.userRepo.save(userToModify);
    }
}
