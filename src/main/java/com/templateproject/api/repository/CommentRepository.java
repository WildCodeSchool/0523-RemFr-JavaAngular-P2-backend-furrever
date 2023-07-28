package com.templateproject.api.repository;

import com.templateproject.api.entity.Comment;

import com.templateproject.api.dto.CommentTemplate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CommentRepository extends JpaRepository<Comment, UUID> {
    @Query("SELECT new com.templateproject.api.dto.CommentTemplate (c.content, c.date, c.note, u.firstname, CONCAT(LEFT(u.lastname, 1), '.')) FROM Comment c JOIN c.transaction t JOIN t.user u WHERE c.note >= 4 ORDER BY c.date DESC LIMIT 3")
    List<CommentTemplate> getLastBestComments();

    @Query("SELECT new com.templateproject.api.dto.CommentTemplate (c.content, c.date, c.note, u.firstname, u.lastname) " +
            "FROM Comment c JOIN c.transaction t JOIN t.user u JOIN t.service s JOIN s.user petsitter " +
            "WHERE petsitter.id = :id AND petsitter.isPetSitter")
    List<CommentTemplate> getCommentsByPetsitterId(@Param("id") UUID id);

    Comment getCommentByTransactionId(UUID id);

    @Query("SELECT new com.templateproject.api.dto.CommentTemplate (c.content, c.date, c.note, u.firstname, u.lastname) " +
            "FROM Comment c JOIN c.transaction t JOIN t.user u JOIN t.service s " +
            "WHERE u.id = :id")
    List<CommentTemplate> getCommentsByUserId(@Param("id") UUID id);
}
