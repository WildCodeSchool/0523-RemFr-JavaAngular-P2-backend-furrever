package com.templateproject.api.repository;

import com.templateproject.api.dto.TransactionUserTemplate;
import com.templateproject.api.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, UUID> {
    @Query("SELECT new com.templateproject.api.dto.TransactionUserTemplate (t.id, t.dateStart, t.dateEnd, t.status, t.content) " +
    "FROM Transaction t JOIN t.user u " +
    "WHERE u.id= :id")
    List<TransactionUserTemplate> getTransactionsByUser(@Param("id") UUID id);
}
