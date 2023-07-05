package com.templateproject.api.controller;

import com.templateproject.api.entity.Service;
import com.templateproject.api.repository.ServiceRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@RestController
@RequestMapping("/api/petsitters/{id}/services")
public class ServiceController {

    private final ServiceRepository serviceRepo;

    public ServiceController(ServiceRepository serviceRepository){
        this.serviceRepo = serviceRepository;
    }
    @PostMapping("/")
    public Service createService(@RequestBody Service service){
        return this.serviceRepo.save(service);
    }

    @PutMapping("/{idService}")
    public Service updateService(@PathVariable UUID idService, @RequestBody Service service){
        this.serviceRepo
                .findById(idService)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return this.serviceRepo.save(service);
    }

    @DeleteMapping("/{idService}")
    public Service deleteService(@PathVariable UUID idService, @RequestBody Service service){
        this.serviceRepo
                .findById(idService)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return this.serviceRepo.save(service);
    }
}
