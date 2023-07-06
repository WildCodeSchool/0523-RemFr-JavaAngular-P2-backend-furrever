package com.templateproject.api.repository;

import com.templateproject.api.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CommentRepository extends JpaRepository<Comment, UUID> {
   // Optional<Comment> findTop3CommentByNoteGreaterThanAndEqualOrderByDateDesc(int note);
}
// Optional<Comment> findCommentByNoteGreaterThanAndEqualOrderByDateDescLimitedTo3(int note);
