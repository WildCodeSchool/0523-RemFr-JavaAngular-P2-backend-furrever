package com.templateproject.api.controller;

import com.templateproject.api.dto.*;
import com.templateproject.api.entity.Animal;
import com.templateproject.api.entity.Location;
import com.templateproject.api.entity.User;
import com.templateproject.api.repository.*;
import com.templateproject.api.service.utils.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
@PreAuthorize("hasAnyAuthority('SCOPE_ROLE_USER', 'SCOPE_ROLE_PETSITTER')")
public class UserController {
    private final UserRepository userRepo;
    private final AnimalRepository animalRepo;
    private final TransactionRepository transactionRepo;
    private final ServiceRepository serviceRepo;
    private final LocationRepository locationRepo;
    private final CommentRepository commentRepo;

    public UserController(UserRepository userRepository, AnimalRepository animalRepository, TransactionRepository transactionRepo, ServiceRepository serviceRepo, LocationRepository locationRepo, CommentRepository commentRepo) {
        this.userRepo = userRepository;
        this.animalRepo = animalRepository;
        this.transactionRepo = transactionRepo;
        this.serviceRepo = serviceRepo;
        this.commentRepo = commentRepo;
        this.locationRepo = locationRepo;
    }

    @GetMapping("")
    public UserProfileResponse getUserByEmail(Principal principal) {
        User user = this.userRepo
                .findByEmail(principal.getName())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Votre utilisateur n'a pas été trouvé."));
        UserProfile userProfile = this.userRepo.getUserById(user.getId());
        Integer countTransactionStatusNull = this.transactionRepo.countTransactionStatusNull(user.getId());
        List<AnimalTemplate> animalTemplateList = this.animalRepo.getAnimalsByUser(user.getId());
        UserProfileResponse finalUser = new UserProfileResponse();
        if (user.getIsPetSitter()){
            List<ServiceTemplate> serviceTemplateList = this.serviceRepo.getServicesByPetsitterId(user.getId());
            finalUser.setServiceTemplateList(serviceTemplateList);
            List<CommentTemplate> commentTemplateList = this.commentRepo.getCommentsByPetsitterId(user.getId());
            finalUser.setCommentTemplateList(commentTemplateList);
        }
        finalUser.setNbTransactionStatusNull(countTransactionStatusNull);
        if (animalTemplateList.size() > 0) {
            finalUser.setAnimalTemplateList(animalTemplateList);
        }
        List<CommentTemplate> commentTemplateListIfIAmUser = this.commentRepo.getCommentsByUserId(user.getId());
        finalUser.setCommentTemplateListIfIAmUser(commentTemplateListIfIAmUser);
        finalUser.setUserProfile(userProfile);
        return finalUser;
    }

    @PutMapping("")
    public User updateUser(Principal principal, @RequestBody User userToModify) {
        User user = this.userRepo
                .findByEmail(principal.getName())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Votre utilisateur n'a pas été trouvé."));
        userToModify.setRoles(null);
        BeanUtils.copyNonNullProperties(userToModify, user);
        if (userToModify.getLocation() != null) {
            Location location = this.locationRepo
                    .findById(userToModify.getLocation().getId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cette adresse n'a pas été trouvée."));
            BeanUtils.copyNonNullProperties(userToModify.getLocation(), location);
            this.locationRepo.save(location);
        }
        return this.userRepo.save(user);
    }

    @PostMapping("/animals")
    @ResponseStatus(HttpStatus.CREATED)
    public Animal createAnimal(Principal principal, @RequestBody Animal animal) {
        User user = this.userRepo
                .findByEmail(principal.getName())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Votre utilisateur n'a pas été trouvé."));
        animal.setUser(user);
        return this.animalRepo.save(animal);
    }

    @PutMapping("/animals/{animalId}")
    public Animal updateAnimal(Principal principal, @PathVariable UUID animalId, @RequestBody Animal animalToModify) {
        User user = this.userRepo
                .findByEmail(principal.getName())
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

    @DeleteMapping("/animals/{animalId}")
    public void delete(Principal principal, @PathVariable UUID animalId) {
        User user = this.userRepo
                .findByEmail(principal.getName())
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
