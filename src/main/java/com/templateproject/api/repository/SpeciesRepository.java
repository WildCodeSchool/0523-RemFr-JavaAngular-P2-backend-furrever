package com.templateproject.api.repository;

import com.templateproject.api.entity.Species;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SpeciesRepository extends JpaRepository<Species, UUID> {
}
