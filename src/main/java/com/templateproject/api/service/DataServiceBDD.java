package com.templateproject.api.service;

import com.templateproject.api.entity.Species;
import com.templateproject.api.entity.Transaction;
import com.templateproject.api.repository.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class DataServiceBDD {
    private final AnimalRepository animalRepository;
    private final CommentRepository commentRepository;
    private final LocationRepository locationRepository;
    private final ServiceRepository serviceRepository;
    private final SpeciesRepository speciesRepository;
    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;


    public DataServiceBDD(
            AnimalRepository animalRepository,
            CommentRepository commentRepository,
            LocationRepository locationRepository,
            ServiceRepository serviceRepository,
            SpeciesRepository speciesRepository,
            TransactionRepository transactionRepository,
            UserRepository userRepository
    ) {
        this.animalRepository = animalRepository;
        this.commentRepository = commentRepository;
        this.locationRepository = locationRepository;
        this.serviceRepository = serviceRepository;
        this.speciesRepository = speciesRepository;
        this.transactionRepository = transactionRepository;
        this.userRepository = userRepository;
    }

    public void addSpecies(){
        List<Species> speciesList = new ArrayList<>();

        Species canin = new Species();
        canin.setName("canin");
        speciesList.add(canin);

        Species felin = new Species();
        felin.setName("félin");
        speciesList.add(felin);

        Species reptile = new Species();
        reptile.setName("reptile");
        speciesList.add(reptile);

        Species bovin = new Species();
        bovin.setName("bovin");
        speciesList.add(bovin);

        Species equidae = new Species();
        equidae.setName("equidae");
        speciesList.add(equidae);

        Species ovide = new Species();
        ovide.setName("ovidé");
        speciesList.add(ovide);

        Species volaille = new Species();
        volaille.setName("volaille");
        speciesList.add(volaille);

        this.speciesRepository.saveAll(speciesList);

    }
}
