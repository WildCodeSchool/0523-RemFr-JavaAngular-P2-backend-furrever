package com.templateproject.api.controller;

import com.templateproject.api.entity.Comment;
import com.templateproject.api.repository.CommentRepository;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class CommentController {
    private final CommentRepository CommentRepo;

    public CommentController(CommentRepository commentRepository) {
        this.CommentRepo = commentRepository;
    }
    
  //  @GetMapping("/api/comments/last-ones")
   // public Optional<Comment> getComments() {
 //       return this.CommentRepo.findTop3CommentByNoteGreaterThanAndEqualOrderByDateDesc(4);
        // Optional<Comment> findCommentByNoteGreaterThanAndEqualOrderByDateDescLimitedTo3(int note);
  //  }
}