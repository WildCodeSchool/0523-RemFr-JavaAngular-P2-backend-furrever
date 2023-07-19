package com.templateproject.api.repository;

import com.templateproject.api.dto.ServiceTemplate;
import com.templateproject.api.entity.Service;
import com.templateproject.api.entity.Species;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ServiceRepository extends JpaRepository<Service, UUID> {
    @Query("SELECT new com.templateproject.api.dto.ServiceTemplate (s.id, s.description, s.price, s.typeService, CAST(s.weightMin AS Float ), CAST(s.weightMax as Float), s.isHealer)" +
           "FROM Service s JOIN s.user u " +
           "WHERE u.id = :id AND u.isPetSitter")
    List<ServiceTemplate> getServicesByPetsitterId(@Param("id") UUID id);

  //  List<Service> findServiceByUserId(UUID id);
}
