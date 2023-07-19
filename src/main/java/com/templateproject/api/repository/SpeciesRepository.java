package com.templateproject.api.repository;

import com.templateproject.api.dto.SpeciesResponse;
import com.templateproject.api.entity.Species;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface SpeciesRepository extends JpaRepository<Species, UUID> {
    @Query("SELECT new com.templateproject.api.dto.SpeciesResponse(s.id, s.name) FROM Species s")
    Optional<List<SpeciesResponse>> findAllSpecies();
}
