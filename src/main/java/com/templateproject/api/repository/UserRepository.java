package com.templateproject.api.repository;

import com.templateproject.api.dto.PetSitterProfile;
import com.templateproject.api.dto.SearchResponse;
import com.templateproject.api.dto.UserProfile;
import com.templateproject.api.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    @Query("SELECT new com.templateproject.api.dto.PetSitterProfile(u.id, u.email, u.firstname, u.lastname, u.description, u.picture, l.street, l.city, l.zipCode) " +
            "FROM User u JOIN u.location l " +
            "WHERE u.isPetSitter AND u.id = :id")
    PetSitterProfile getPetSitterById(@Param("id") UUID id);

    @Query("SELECT new com.templateproject.api.dto.UserProfile(u.id, u.email, u.firstname, u.lastname, u.description, u.picture, u.isPetSitter, u.location) " +
            "FROM User u LEFT JOIN u.location l " +
            "WHERE u.id = :id")
    UserProfile getUserById(@Param("id") UUID id);

    Optional<User> findByEmail(String email);

    @Query("SELECT new com.templateproject.api.dto.SearchResponse(u.id, u.email, u.firstname, u.lastname, u.description, u.picture, MIN(s.price), CAST(AVG(c.note) AS int), CAST(COUNT(c.note) AS int)) " +
            "FROM User u JOIN u.services s JOIN s.speciesList sp JOIN u.location l  LEFT JOIN s.transactions t LEFT JOIN t.comment c " +
            "WHERE u.isPetSitter AND l.city = :city AND s.typeService = :typeService AND sp.name IN :speciesList GROUP BY u.id ORDER BY MIN(s.price) ASC ")
    List<SearchResponse> getPetSitterBySearch(@Param("city") String city, @Param("typeService") String typeService, @Param("speciesList") List<String> speciesList);
}
