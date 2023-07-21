package com.templateproject.api.controller;

import com.templateproject.api.dto.*;
import com.templateproject.api.entity.Species;
import com.templateproject.api.repository.CommentRepository;
import com.templateproject.api.repository.ServiceRepository;
import com.templateproject.api.repository.SpeciesRepository;
import com.templateproject.api.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/petsitters")
public class PetsitterController {

    private final UserRepository userRepo;
    private final CommentRepository commentRepo;
    private final SpeciesRepository speciesRepo;
    private final ServiceRepository serviceRepo;

    public PetsitterController(UserRepository userRepository, CommentRepository commentRepo, SpeciesRepository speciesRepo, ServiceRepository serviceRepo) {
        this.userRepo = userRepository;
        this.commentRepo = commentRepo;
        this.speciesRepo = speciesRepo;
        this.serviceRepo = serviceRepo;
    }

    @GetMapping("/{petsitterId}")
    public PetSitterProfileResponse getPetSitterById(@PathVariable UUID petsitterId) {
        PetSitterProfile petSitterProfile = this.userRepo.getPetSitterById(petsitterId);
        List<CommentTemplate> commentTemplateList = this.commentRepo.getCommentsByPetsitterId(petsitterId);
        List<ServiceTemplate> serviceTemplateList = this.serviceRepo.getServicesByPetsitterId(petsitterId);
        for (ServiceTemplate service : serviceTemplateList){
            List<SpeciesResponse> speciesResponseList = this.speciesRepo.findSpeciesByServiceId(service.getId());
            service.setSpeciesList(speciesResponseList);
        }
        return new PetSitterProfileResponse(petSitterProfile, commentTemplateList, serviceTemplateList);
    }

    @PostMapping("/search")
    public List<SearchResponse> getPetSitters(@RequestBody SearchRequest searchRequest) {
        if (searchRequest.getCity() == null ){
            searchRequest.setCity("tours");
        }
        if (searchRequest.getTypeService() == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Nous n'avons pas trouvé le type de service demandé.");
        }
        return this.userRepo
                .getPetSitterBySearch(searchRequest.getCity(), searchRequest.getTypeService(), searchRequest.getSpeciesList());
    }
}
