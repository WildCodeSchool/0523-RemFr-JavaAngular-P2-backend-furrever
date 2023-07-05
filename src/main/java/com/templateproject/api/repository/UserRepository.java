package com.templateproject.api.repository;

import com.templateproject.api.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    User findUserByIdAndIsPetSitter(UUID id, Boolean isPetSitter);

}
