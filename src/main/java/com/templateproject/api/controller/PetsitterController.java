package com.templateproject.api.controller;

import com.templateproject.api.entity.User;
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
    private final UserController userController;

    public PetsitterController(UserRepository userRepository, UserController userController){
        this.userRepo = userRepository;
        this.userController = userController;
    }

    @GetMapping("/{id}")
    public User getPetSitterById(@PathVariable UUID id){
        if(id != null) {
            this.userRepo.findUserByIdAndIsPetSitter(id, true);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public User updatePetsitter(@PathVariable UUID id, @RequestBody User userToModify){
        if (id != null){
            return this.userController.updateUser(id, userToModify);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

}
