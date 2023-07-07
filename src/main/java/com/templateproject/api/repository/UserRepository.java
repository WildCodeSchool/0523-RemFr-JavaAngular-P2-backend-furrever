package com.templateproject.api.repository;

import com.templateproject.api.entity.User;
import org.hibernate.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User>  findUserByIdAndIsPetSitter(UUID id, Boolean isPetSitter);
}
