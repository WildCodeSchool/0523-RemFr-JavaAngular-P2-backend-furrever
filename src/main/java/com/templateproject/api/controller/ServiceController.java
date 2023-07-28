package com.templateproject.api.controller;

import com.templateproject.api.entity.Service;
import com.templateproject.api.entity.Transaction;
import com.templateproject.api.entity.User;
import com.templateproject.api.repository.ServiceRepository;
import com.templateproject.api.repository.TransactionRepository;
import com.templateproject.api.repository.UserRepository;
import com.templateproject.api.service.utils.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.List;
import java.util.UUID;

@RestController
@PreAuthorize("hasAnyAuthority('SCOPE_ROLE_USER', 'SCOPE_ROLE_PETSITTER')")
@RequestMapping("/api/services")
public class ServiceController {

    private final ServiceRepository serviceRepo;
    private final UserRepository userRepo;
    private final TransactionRepository transactionRepo;

    public ServiceController(ServiceRepository serviceRepo, UserRepository userRepo, TransactionRepository transactionRepo) {
        this.serviceRepo = serviceRepo;
        this.userRepo = userRepo;
        this.transactionRepo = transactionRepo;
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public Service createService(Principal principal, @RequestBody Service service) {
        User petsitter = this.userRepo
                .findByEmail(principal.getName())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Votre utilisateur n'a pas été trouvé."));
        if (petsitter.getIsPetSitter()) {
            service.setUser(petsitter);
            return this.serviceRepo.save(service);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Vous n'êtes pas autorisé a créer un service!");
    }

    @PutMapping("/{serviceId}")
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

    @DeleteMapping("/{serviceId}")
    public void deleteService(Principal principal, @PathVariable UUID serviceId) {
        User petsitter = this.userRepo
                .findByEmail(principal.getName())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Votre utilisateur n'a pas été trouvé."));

        Service serviceInBdd = this.serviceRepo
                .findById(serviceId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Le service n'a pas été supprimé."));
        Integer nbTransaction = this.transactionRepo.countTransactionByServiceId(serviceInBdd.getId());
        if (petsitter.getServices().contains(serviceInBdd) && nbTransaction == 0 ) {
            this.serviceRepo.deleteById(serviceId);
        } else {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Ce service possède une transaction ou ne vous appartient pas, vous ne pouvez pas le supprimer.");
        }
    }
}
