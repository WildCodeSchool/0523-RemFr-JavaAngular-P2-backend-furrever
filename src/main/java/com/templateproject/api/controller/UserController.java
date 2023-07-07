package com.templateproject.api.controller;

import com.templateproject.api.entity.Animal;
import com.templateproject.api.entity.User;
import com.templateproject.api.repository.AnimalRepository;
import com.templateproject.api.repository.UserRepository;
import com.templateproject.api.service.utils.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@RestController
@RequestMapping("/api/users")

public class UserController {
    private final UserRepository userRepo;
    private final AnimalRepository animalRepo;

    public UserController(UserRepository userRepository, AnimalRepository animalRepository) {
        this.userRepo = userRepository;
        this.animalRepo = animalRepository;

    }

    @PostMapping("/")
    public User createUser(@RequestBody User user) {
        return this.userRepo.save(user);
    }

    @GetMapping("/")
    public List<User> index() {
        return this.userRepo.findAll();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable UUID id) {
        return this.userRepo
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable UUID id, @RequestBody User userToModify) {
        User user =  this.userRepo
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        BeanUtils.copyNonNullProperties(userToModify, user);
        return this.userRepo.save(user);
    }

    @PostMapping("/{id}/animals")
    public Animal createAnimal(@RequestBody Animal animal) {
        return this.animalRepo.save(animal);
    }

    @PutMapping("/{id}/animals/{animalId}")
    public Animal updateAnimal(@PathVariable UUID id, @PathVariable UUID animalId, @RequestBody Animal animalToModify) {
        User user = this.userRepo
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Votre utilisateur n'a pas été trouvé."));
        this.animalRepo
                .findById(animalId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Votre animal n'a pas été mis à jour."));
        animalToModify.setUser(user);
        return this.animalRepo.save(animalToModify);
    }

    @DeleteMapping("/{id}/animals/{animalId}")
    public void delete(@PathVariable UUID id, @PathVariable UUID animalId) {
        this.animalRepo
                .findById(animalId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Votre animal n'a pas été supprimé."));
        this.animalRepo.deleteById(animalId);
    }

}
