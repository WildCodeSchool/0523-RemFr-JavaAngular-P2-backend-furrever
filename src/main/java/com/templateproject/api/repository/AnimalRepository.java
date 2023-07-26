package com.templateproject.api.repository;

import com.templateproject.api.dto.AnimalTemplate;
import com.templateproject.api.entity.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, UUID> {
    @Query("SELECT new com.templateproject.api.dto.AnimalTemplate (a.id, a.firstname, a.birthday, CAST(a.weight AS float), a.description, s.name) " +
    "FROM Animal a JOIN a.user u JOIN a.species s " +
    "WHERE u.id = :id")
    List<AnimalTemplate> getAnimalsByUser(@Param("id") UUID id);
}
