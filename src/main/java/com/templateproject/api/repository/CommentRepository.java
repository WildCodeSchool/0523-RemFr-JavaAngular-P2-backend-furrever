package com.templateproject.api.repository;

import com.templateproject.api.entity.Comment;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CommentRepository extends JpaRepository<Comment, UUID> {



    @Query("SELECT c.content, CAST(c.date AS DATE), c.note, u.firstName, CONCAT(LEFT(u.lastName, 1), '.')  FROM Comment c JOIN c.transaction t JOIN t.user u WHERE c.note >= 4 ORDER BY c.date DESC LIMIT 3")
    Optional<List<String>> getLastBestComments();

}
