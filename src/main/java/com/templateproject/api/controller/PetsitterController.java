package com.templateproject.api.controller;

import com.templateproject.api.entity.Service;
import com.templateproject.api.entity.User;
import com.templateproject.api.repository.ServiceRepository;
import com.templateproject.api.repository.UserRepository;
import com.templateproject.api.service.utils.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@RestController
@RequestMapping("/api/petsitters")
public class PetsitterController {

    private final UserRepository userRepo;
    private final ServiceRepository serviceRepo;

    public PetsitterController(UserRepository userRepository, ServiceRepository serviceRepository){
        this.userRepo = userRepository;
        this.serviceRepo = serviceRepository;
    }

    @GetMapping("/{petsitterId}")
    public User getPetSitterById(@PathVariable UUID petsitterId){
            return this.userRepo
                    .findUserByIdAndIsPetSitter(petsitterId, true)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Le petsitter n'a pas été trouvé."));
    }

    @PutMapping("/{petsitterId}")
    public User updatePetsitter(@PathVariable UUID petsitterId, @RequestBody User userToModify){
        User user = this.userRepo
                .findById(petsitterId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        BeanUtils.copyNonNullProperties(userToModify, user);
        return this.userRepo.save(user);
    }

    @PostMapping("/{petsitterId}/services")
    @ResponseStatus(HttpStatus.CREATED)
    public Service createService(@PathVariable UUID petsitterId, @RequestBody Service service){
        User petsitter = this.userRepo
                .findById(petsitterId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Le petsitter n'a pas été trouvé."));
        if(petsitter.getPetSitter()){
            service.setUser(petsitter);
            return this.serviceRepo.save(service);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Vous n'êtes pas autorisé a créer un service!");
    }

    @PutMapping("/{petsitterId}/services/{serviceId}")
    public Service updateService(@PathVariable UUID petsitterId, @PathVariable UUID serviceId, @RequestBody Service serviceToModify){
        Service serviceInBdd = this.serviceRepo
                .findById(serviceId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Le service n'a pas été trouvé."));

        User petsitter = this.userRepo
                .findById(petsitterId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Le petsitter n'a pas été trouvé."));

        if(petsitter.getServices().contains(serviceInBdd)) {
            BeanUtils.copyNonNullProperties(serviceToModify, serviceInBdd);
            return this.serviceRepo.save(serviceInBdd);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Ce service n'est pas le vôtre.");
        }
    }

    @DeleteMapping("/{petsitterId}/services/{serviceId}")
    public void deleteService(@PathVariable UUID petsitterId, @PathVariable UUID serviceId){
        User petsitter = this.userRepo
                .findById(petsitterId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Le petsitter n'a pas été trouvé."));

        Service serviceInBdd = this.serviceRepo
                .findById(serviceId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Le service n'a pas été supprimé."));

        if (petsitter.getServices().contains(serviceInBdd)){
            this.serviceRepo.deleteById(serviceId);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Ce service ne vous appartient pas, vous ne pouvez pas le supprimer.");
        }
    }
}
