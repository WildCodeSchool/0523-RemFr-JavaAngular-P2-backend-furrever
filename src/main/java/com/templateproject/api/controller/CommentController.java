package com.templateproject.api.controller;

import com.templateproject.api.entity.CommentTemplate;
import com.templateproject.api.repository.CommentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.lang.reflect.Array;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CommentController {
    private final CommentRepository commentRepo;

    public CommentController(CommentRepository commentRepository) {
        this.commentRepo = commentRepository;
    }

    @GetMapping("/comments/top")
    public List<String> getComments() {
        List<String> CommentStringList = this.commentRepo
                .getLastBestComments()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

       

    }
}