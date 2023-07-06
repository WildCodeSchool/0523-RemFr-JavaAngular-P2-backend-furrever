package com.templateproject.api.controller;

import com.templateproject.api.entity.Comment;
import com.templateproject.api.repository.CommentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@RestController
public class CommentController {
    private final CommentRepository CommentRepo;

    public CommentController(CommentRepository commentRepository) {
        this.CommentRepo = commentRepository;
    }
    
    @GetMapping("/api/comments/last-ones")
    public List<Comment> getComments() {
        return this.CommentRepo.findTop3CommentByNoteGreaterThanAndEqualOrderByDateDesc(4);
        // Optional<Comment> findCommentByNoteGreaterThanAndEqualOrderByDateDescLimitedTo3(int note);
    }
}