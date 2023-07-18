package com.templateproject.api.repository;

import com.templateproject.api.entity.Service;
import com.templateproject.api.entity.User;
import jdk.jfr.Name;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findUserByIdAndIsPetSitter(UUID id, Boolean isPetSitter);
    Optional<List<User>> findAllByIsPetSitter(Boolean isPetSitter);
    Optional<User> findByEmail(String email);

    @Query("SELECT u FROM User u JOIN u.services s JOIN s.speciesList sp WHERE u.isPetSitter AND sp.name = 'Volaille'")
    List<User> getPetSitterBySearch();
}

//s.typeService = 'garde prolongée' AND l.city = 'Saint Cyr sur Loire' AND
