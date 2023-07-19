package com.templateproject.api;

import com.github.javafaker.Faker;
import com.templateproject.api.entity.*;
import com.templateproject.api.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class SampleDataLoader implements CommandLineRunner {

    private final Faker faker;
    private List<String> typeService = new ArrayList<>();
    private final SpeciesRepository speciesRepository;
    private final UserRepository userRepository;
    private final LocationRepository locationRepository;
    private final AnimalRepository animalRepository;
    private final ServiceRepository serviceRepository;
    private final TransactionRepository transactionRepository;
    private final CommentRepository commentRepository;

    public SampleDataLoader(
            SpeciesRepository speciesRepository,
            UserRepository userRepository,
            LocationRepository locationRepository,
            AnimalRepository animalRepository,
            ServiceRepository serviceRepository,
            TransactionRepository transactionRepository,
            CommentRepository commentRepository
    ) {
        this.faker = new Faker();
        this.typeService.add("promenade quotidienne");
        this.typeService.add("visite à domicile");
        this.typeService.add("garde prolongée");
        this.speciesRepository = speciesRepository;
        this.userRepository = userRepository;
        this.locationRepository = locationRepository;
        this.animalRepository = animalRepository;
        this.serviceRepository = serviceRepository;
        this.transactionRepository = transactionRepository;
        this.commentRepository = commentRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (this.speciesRepository.count() > 0){
            return;
        }
        List<Species> speciesList = this.speciesData();
        List<Location> locationList = this.locationData();
        List<Location> locationListTours = this.locationDataTours();
        List<User> userList = this.userData(locationList, locationListTours);
        List<User> petsitterList = this.petsitterData(locationList, locationListTours);
        this.animalData(userList, speciesList);
        List<Service> serviceList = this.serviceData(petsitterList,speciesList);
        List<Transaction> transactionList = this.transactionData(userList, serviceList);
        this.commentData(transactionList);
    }

    private List<Species> speciesData() {
        List<Species> speciesList = new ArrayList<>();

        Species canin = new Species();
        canin.setName("Canin");
        speciesList.add(canin);

        Species felin = new Species();
        felin.setName("Félin");
        speciesList.add(felin);

        Species reptile = new Species();
        reptile.setName("Reptile");
        speciesList.add(reptile);

        Species bovin = new Species();
        bovin.setName("Bovin");
        speciesList.add(bovin);

        Species equidae = new Species();
        equidae.setName("Équidé");
        speciesList.add(equidae);

        Species volaille = new Species();
        volaille.setName("Volaille");
        speciesList.add(volaille);

        this.speciesRepository.saveAll(speciesList);
        return speciesList;
    }

    private List<User> userData(List<Location> locationList, List<Location> locationListTours) {
        List<User> userList = IntStream.rangeClosed(1, 50)
                .mapToObj(i -> {
                    String firstName = this.faker.name().firstName();
                    String lastName = this.faker.name().lastName();
                    User user = new User();
                    user.setFirstName(firstName);
                    user.setLastName(lastName);
                    user.setDescription("Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis");
                    user.setEmail(firstName + lastName + "@api.com");
                    user.setPassword("password");
                    if (this.faker.random().nextBoolean()) {
                        user.setLocation(locationList.get(0));
                        locationList.remove(0);
                    } else {
                        user.setLocation(locationListTours.get(0));
                        locationListTours.remove(0);
                    }
                    return user;
                })
                .collect(Collectors.toList());
        return this.userRepository.saveAll(userList);
    }

    private List<User> petsitterData(List<Location> locationList, List<Location> locationListTours) {
        List<User> userList = IntStream.rangeClosed(1, 20)
                .mapToObj(i -> {
                    String firstName = this.faker.name().firstName();
                    String lastName = this.faker.name().lastName();
                    User user = new User();
                    user.setFirstName(firstName);
                    user.setLastName(lastName);
                    user.setDescription("Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis");
                    user.setEmail(firstName + lastName + "@api.com");
                    user.setPassword("password");
                    user.setPetSitter(true);
                    if (this.faker.random().nextBoolean()) {
                        user.setLocation(locationList.get(0));
                        locationList.remove(0);
                    } else {
                        user.setLocation(locationListTours.get(0));
                        locationListTours.remove(0);
                    }
                    return user;
                })
                .collect(Collectors.toList());
        return this.userRepository.saveAll(userList);
    }

    private List<Location> locationData() {
        List<Location> locationList = IntStream.rangeClosed(1, 70)
                .mapToObj(i -> {
                    Location location = new Location();
                    location.setStreetNumber(String.valueOf(this.faker.number().numberBetween(1, 1000)));
                    location.setStreet("rue de " + this.faker.dragonBall().character());
                    location.setZipCode("37540");
                    location.setCity("Saint Cyr sur Loire");
                    return location;
                })
                .collect(Collectors.toList());
        return this.locationRepository.saveAll(locationList);
    }

    private List<Location> locationDataTours() {
        List<Location> locationToursList = IntStream.rangeClosed(1, 70)
                .mapToObj(i -> {
                    Location toursLocation = new Location();
                    toursLocation.setStreetNumber(String.valueOf(this.faker.number().numberBetween(1, 1000)));
                    toursLocation.setStreet("rue de " + this.faker.dragonBall().character());
                    toursLocation.setZipCode("37000");
                    toursLocation.setCity("Tours");
                    return toursLocation;
                })
                .collect(Collectors.toList());
        return this.locationRepository.saveAll(locationToursList);
    }

    private void animalData(List<User> userList, List<Species> speciesList) {
        List<Animal> animalList = IntStream.rangeClosed(1, 150)
                .mapToObj(i -> {
                    Collections.shuffle(userList);
                    Collections.shuffle(speciesList);
                    long minDay = LocalDate.of(2008, 1, 1).toEpochDay();
                    long maxDay = LocalDate.of(2023, 7, 1).toEpochDay();
                    long randomDay = ThreadLocalRandom.current().nextLong(minDay, maxDay);
                    LocalDate randomDate = LocalDate.ofEpochDay(randomDay);
                    Animal animal = new Animal();
                    animal.setFirstName(this.faker.gameOfThrones().character());
                    animal.setBirthday(randomDate);
                    animal.setWeight((float) this.faker.number().numberBetween((int) 1.5, (int) 120.0));
                    animal.setDescription("Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor.");
                    animal.setSpecies(speciesList.get(0));
                    animal.setUser(userList.get(0));
                    return animal;
                })
                .collect(Collectors.toList());
        this.animalRepository.saveAll(animalList);
    }

    private List<Service> serviceData(List<User> petsitterList, List<Species> speciesList) {
        List<Species> speciesList1 = new ArrayList<>();
        speciesList1.add(speciesList.get(0));
        speciesList1.add(speciesList.get(1));

        List<Species> speciesList2 = new ArrayList<>();
        speciesList2.add(speciesList.get(2));

        List<Species> speciesList3 = new ArrayList<>();
        speciesList3.add(speciesList.get(3));
        speciesList3.add(speciesList.get(4));
        speciesList3.add(speciesList.get(5));

        List<Species> speciesList4 = new ArrayList<>();
        speciesList4.add(speciesList.get(1));
        speciesList4.add(speciesList.get(5));

        List<List<Species>> listOfSpeciesList = new ArrayList<>();
        listOfSpeciesList.add(speciesList1);
        listOfSpeciesList.add(speciesList2);
        listOfSpeciesList.add(speciesList3);
        listOfSpeciesList.add(speciesList4);
        List<Service> serviceList = IntStream.rangeClosed(1, 80)
                .mapToObj(i -> {
                    Collections.shuffle(petsitterList);
                    Collections.shuffle(listOfSpeciesList);
                    Collections.shuffle(this.typeService);
                    Service service = new Service();
                    service.setDescription("Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor.");
                    service.setPrice((float) this.faker.number().numberBetween(20, 120));
                    service.setTypeService(this.typeService.get(0));
                    service.setHealer(faker.random().nextBoolean());
                    service.setSpeciesList(listOfSpeciesList.get(0));
                    service.setUser(petsitterList.get(0));
                    return service;
                })
                .collect(Collectors.toList());
        return this.serviceRepository.saveAll(serviceList);
    }

    private List<Transaction> transactionData(List<User> userList, List<Service> serviceList) {
        List<Transaction> transactionList = IntStream.rangeClosed(1, 200)
                .mapToObj(i -> {
                    long minDay = LocalDate.of(2023, 5, 1).toEpochDay();
                    long maxDay = LocalDate.of(2023, 7, 1).toEpochDay();
                    long randomDay = ThreadLocalRandom.current().nextLong(minDay, maxDay);
                    LocalDate dateStart = LocalDate.ofEpochDay(minDay);
                    LocalDate randomDate = LocalDate.ofEpochDay(randomDay);
                    Collections.shuffle(userList);
                    Collections.shuffle(serviceList);
                    Transaction transaction = new Transaction();
                    transaction.setDateStart(dateStart);
                    transaction.setDateEnd(randomDate);
                    transaction.setStatus(this.faker.random().nextBoolean());
                    transaction.setUser(userList.get(0));
                    transaction.setService(serviceList.get(0));
                    return transaction;
                })
                .collect(Collectors.toList());
        return this.transactionRepository.saveAll(transactionList);
    }

    private void commentData(List<Transaction> transactionList) {
        List<Transaction> transactionListSort = new ArrayList<>();
        for (Transaction transaction : transactionList){
            if(transaction.getStatus()){
                transactionListSort.add(transaction);
            }
        }
        List<Comment> commentList = IntStream.rangeClosed(1, transactionListSort.size())
                .mapToObj(i -> {
                    Collections.shuffle(transactionListSort);
                    Comment comment = new Comment();
                    comment.setContent("Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor.");
                    comment.setNote(this.faker.random().nextInt(2, 5));
                    comment.setTransaction(transactionListSort.get(0));
                    comment.setDate(transactionListSort.get(0).getDateEnd());
                    transactionListSort.remove(0);
                    return  comment;
                })
                .collect(Collectors.toList());
        this.commentRepository.saveAll(commentList);
    }

}
