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
    @Query("SELECT new com.templateproject.api.dto.TransactionUserTemplate (t.id, t.dateStart, t.dateEnd, t.status, t.content, s.typeService, s.price, u.firstName, u.lastName, u.email, t.service.user.firstName, t.service.user.lastName) " +
    "FROM Transaction t JOIN t.user u JOIN t.service s " +
    "WHERE u.id= :id")
    List<TransactionUserTemplate> getTransactionsByUser(@Param("id") UUID id);

    @Query("SELECT new com.templateproject.api.dto.TransactionUserTemplate (t.id, t.dateStart, t.dateEnd, t.status, t.content, s.typeService, s.price, u.firstName, u.lastName, u.email, uPetsitter.firstName, uPetsitter.lastName) " +
            "FROM Transaction t JOIN t.service s JOIN s.user uPetsitter JOIN t.user u " +
            "WHERE uPetsitter.id= :id")
    List<TransactionUserTemplate> getTransactionsForPetsitter(@Param("id") UUID id);

    @Query("SELECT count(t.id) FROM Transaction t " +
            "JOIN t.service s JOIN s.user u " +
            "WHERE u.id = :id AND t.status IS NULL")
    Integer countTransactionStatusNull(@Param("id") UUID id);
}
