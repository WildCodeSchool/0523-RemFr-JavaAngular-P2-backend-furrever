package com.templateproject.api;

import com.github.javafaker.Faker;
import com.templateproject.api.entity.Animal;
import com.templateproject.api.entity.Location;
import com.templateproject.api.entity.Species;
import com.templateproject.api.entity.User;
import com.templateproject.api.repository.AnimalRepository;
import com.templateproject.api.repository.LocationRepository;
import com.templateproject.api.repository.SpeciesRepository;
import com.templateproject.api.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class SampleDataLoader implements CommandLineRunner {

    private final Faker faker;
    private final SpeciesRepository speciesRepository;
    private final UserRepository userRepository;
    private final LocationRepository locationRepository;
    private final AnimalRepository animalRepository;


    public SampleDataLoader(
            SpeciesRepository speciesRepository,
            UserRepository userRepository,
            LocationRepository locationRepository,
            AnimalRepository animalRepository
    ) {
        this.faker = new Faker();
        this.speciesRepository = speciesRepository;
        this.userRepository = userRepository;
        this.locationRepository = locationRepository;
        this.animalRepository = animalRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        List<Species> speciesList = this.speciesData();
        List<Location> locationList = this.locationData();
        List<User> userList = this.userData(locationList);
        List<User> petsitterList = this.petsitterData(locationList);
    }

    private List<Species> speciesData() {
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

        Species volaille = new Species();
        volaille.setName("volaille");
        speciesList.add(volaille);

        this.speciesRepository.saveAll(speciesList);
        return speciesList;
    }

    private List<User> userData(List<Location> locationList) {
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
                    user.setLocation(locationList.get(0));
                    locationList.remove(0);

                    return user;
                })
                .collect(Collectors.toList());
        return this.userRepository.saveAll(userList);
    }

    private List<User> petsitterData(List<Location> locationList) {
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
                    user.setLocation(locationList.get(0));
                    locationList.remove(0);
                    return user;
                })
                .collect(Collectors.toList());
        return this.userRepository.saveAll(userList);
    }

    private List<Location> locationData() {
        List<Location> locationList = IntStream.rangeClosed(1, 70)
                .mapToObj(i -> {
                    Location location = new Location();
                    location.setStreetNumber(String.valueOf(this.faker.number().numberBetween(1,1000)));
                    location.setStreet("rue de " + this.faker.dragonBall().character());
                    location.setZipCode("37540");
                    location.setCity("Saint Cyr sur Loire");
                    return location;
                })
                .collect(Collectors.toList());
        return this.locationRepository.saveAll(locationList);
    }

  /*  private List<Animal> animalData(List<User> userList, List<Species> speciesList) {
        double index = Math.random() * 50 ;
        List<Animal> animalList = IntStream.rangeClosed(1,100)
                .mapToObj(i -> {
                    long minDay = LocalDate.of(2008, 1, 1).toEpochDay();
                    long maxDay = LocalDate.of(2023, 7, 1).toEpochDay();
                    long randomDay = ThreadLocalRandom.current().nextLong(minDay, maxDay);
                    LocalDate randomDate = LocalDate.ofEpochDay(randomDay);
                    Animal animal = new Animal();
                    animal.setFirstName(this.faker.gameOfThrones().character());
                    animal.setBirthday(randomDate);
                    animal.setWeight((float) this.faker.number().numberBetween((int) 1.5, (int) 120.0));
                    animal.setDescription("Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor.");


                })
    }*/




}
