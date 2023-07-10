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
    @ResponseStatus(HttpStatus.CREATED)
    public User createUser(@RequestBody User user) {
        return this.userRepo.save(user);
    }

    @GetMapping("/")
    public List<User> index() {
        return this.userRepo.findAll();
    }

    @GetMapping("/{userId}")
    public User getUserById(@PathVariable UUID userId) {
        return this.userRepo
                .findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{userId}")
    public User updateUser(@PathVariable UUID userId, @RequestBody User userToModify) {
        User user = this.userRepo
                .findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        BeanUtils.copyNonNullProperties(userToModify, user);
        return this.userRepo.save(user);
    }

    @PostMapping("/{userId}/animals")
    @ResponseStatus(HttpStatus.CREATED)
    public Animal createAnimal(@RequestBody Animal animal) {
        return this.animalRepo.save(animal);
    }

    @PutMapping("/{userId}/animals/{animalId}")
    public Animal updateAnimal(@PathVariable UUID userId, @PathVariable UUID animalId, @RequestBody Animal animalToModify) {
        User user = this.userRepo
                .findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Votre utilisateur n'a pas été trouvé."));

        Animal animal = this.animalRepo
                .findById(animalId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Votre animal n'a pas été mis à jour."));

        if (user.getAnimals().contains(animal)) {
            BeanUtils.copyNonNullProperties(animalToModify, animal);
            return this.animalRepo.save(animal);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cet animal ne vous appartient pas.");
        }
    }

    @DeleteMapping("/{userId}/animals/{animalId}")
    public void delete(@PathVariable UUID userId, @PathVariable UUID animalId) {
        User user = this.userRepo
                .findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Votre utilisateur n'a pas été trouvé."));

        Animal animal = this.animalRepo
                .findById(animalId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Votre animal n'a pas été supprimé."));

        if (user.getAnimals().contains(animal)) {
            this.animalRepo.deleteById(animalId);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cet animal ne vous appartient pas, vous ne pouvez pas le supprimer.");
        }
    }

}
