package com.templateproject.api.repository;

import com.templateproject.api.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findUserByIdAndIsPetSitter(UUID id, Boolean isPetSitter);
    Optional<List<User>> findAllByIsPetSitter(Boolean isPetSitter);
    Optional<User> findByEmail(String email);
}
