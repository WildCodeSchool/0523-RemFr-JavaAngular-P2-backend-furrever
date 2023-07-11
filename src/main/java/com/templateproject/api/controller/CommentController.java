package com.templateproject.api.controller;

import com.templateproject.api.entity.CommentTemplate;
import com.templateproject.api.repository.CommentRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CommentController {
    private final CommentRepository commentRepo;

    public CommentController(CommentRepository commentRepository) {
        this.commentRepo = commentRepository;
    }

    @GetMapping("/comments/top")
    public List<CommentTemplate> getComments() {
        return this.commentRepo.getLastBestComments();
    }
}