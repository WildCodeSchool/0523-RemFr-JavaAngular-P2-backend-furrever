package com.templateproject.api.controller;

import com.templateproject.api.entity.Animal;
import com.templateproject.api.entity.User;
import com.templateproject.api.repository.AnimalRepository;
import com.templateproject.api.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/users")

public class UserController {

    private final UserRepository userRepo;
    private final AnimalRepository animalRepo;

    public UserController(UserRepository userRepository, AnimalRepository animalRepository){
        this.userRepo = userRepository;
        this.animalRepo = animalRepository;
    }

    @PostMapping("/")
    public User createUser(@RequestBody User user) {
        return this.userRepo.save(user);
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable UUID id){
        if(id != null){
            Optional<User> userOptional = this.userRepo.findById(id);
            if(userOptional.isPresent()) {
                return userOptional.get();
            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable UUID id, @RequestBody User userToModify){
        if(id != null) {
            Optional<User> userOptional = this.userRepo.findById(id);
            if(userOptional.isPresent()){
                User userToUpdate = userOptional.get();
                userToUpdate.setDescription(userToModify.getDescription());
                userToUpdate.setEmail(userToModify.getEmail());
                userToUpdate.setLocation(userToModify.getLocation());
                userToUpdate.setFirstName(userToModify.getFirstName());
                userToUpdate.setLastName(userToModify.getLastName());
                userToUpdate.setPicture(userToModify.getPicture());
                userToUpdate.setPetSitter(userToModify.getPetSitter());
                return this.userRepo.save(userToUpdate);
            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/{id}/animals")
    public Animal createAnimal(@RequestBody Animal animal) {
        return this.animalRepo.save(animal);
    }

    @PutMapping("/{id}/animals/{animalId}")
    public Animal updateAnimal(@PathVariable UUID id, @PathVariable UUID animalId, @RequestBody Animal animalToModify) {
        if (id != null && animalId != null){
            Optional<Animal> animalOptional = this.animalRepo.findById(animalId);
            if(animalOptional.isPresent()){
                Animal animalToUpdate = animalOptional.get();
                animalToUpdate.setFirstName(animalToModify.getFirstName());
                animalToUpdate.setBirthday(animalToUpdate.getBirthday());
                animalToUpdate.setSpecies(animalToModify.getSpecies());
                animalToUpdate.setWeight(animalToModify.getWeight());
                animalToUpdate.setDescription(animalToModify.getDescription());
                return this.animalRepo.save(animalToUpdate);
            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}/animals/{animalId}")
    public void delete(@PathVariable UUID id, @PathVariable UUID animalId) {
        if(id != null && animalId != null){
            Optional<Animal> animalOptional = this.animalRepo.findById(animalId);
                  if(animalOptional.isPresent()){
                      this.animalRepo.deleteById(animalId);
                  }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

}
