package com.templateproject.api.controller;

import com.templateproject.api.entity.Service;
import com.templateproject.api.entity.User;
import com.templateproject.api.repository.ServiceRepository;
import com.templateproject.api.repository.UserRepository;
import com.templateproject.api.service.utils.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.UUID;

@RestController
@RequestMapping("/api/services")
public class ServiceController {

    private final ServiceRepository serviceRepo;
    private final UserRepository userRepo;

    public ServiceController(ServiceRepository serviceRepo, UserRepository userRepo) {
        this.serviceRepo = serviceRepo;
        this.userRepo = userRepo;
    }

    @PostMapping("/services")
    @PreAuthorize("hasAuthority('SCOPE_ROLE_PETSITTER')")
    @ResponseStatus(HttpStatus.CREATED)
    public Service createService(Principal principal, @RequestBody Service service) {
        User petsitter = this.userRepo
                .findByEmail(principal.getName())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Votre utilisateur n'a pas été trouvé."));
        if (petsitter.getPetSitter()) {
            service.setUser(petsitter);
            return this.serviceRepo.save(service);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Vous n'êtes pas autorisé a créer un service!");
    }

    @PutMapping("/services/{serviceId}")
    @PreAuthorize("hasAuthority('SCOPE_ROLE_PETSITTER')")
    public Service updateService(Principal principal, @PathVariable UUID serviceId, @RequestBody Service serviceToModify) {
        Service serviceInBdd = this.serviceRepo
                .findById(serviceId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Le service n'a pas été trouvé."));

        User petsitter = this.userRepo
                .findByEmail(principal.getName())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Votre utilisateur n'a pas été trouvé."));

        if (petsitter.getServices().contains(serviceInBdd)) {
            BeanUtils.copyNonNullProperties(serviceToModify, serviceInBdd);
            return this.serviceRepo.save(serviceInBdd);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Ce service n'est pas le vôtre.");
        }
    }

    @DeleteMapping("/services/{serviceId}")
    @PreAuthorize("hasAuthority('SCOPE_ROLE_PETSITTER')")
    public void deleteService(Principal principal, @PathVariable UUID serviceId) {
        User petsitter = this.userRepo
                .findByEmail(principal.getName())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Votre utilisateur n'a pas été trouvé."));

        Service serviceInBdd = this.serviceRepo
                .findById(serviceId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Le service n'a pas été supprimé."));

        if (petsitter.getServices().contains(serviceInBdd)) {
            this.serviceRepo.deleteById(serviceId);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Ce service ne vous appartient pas, vous ne pouvez pas le supprimer.");
        }
    }
}
