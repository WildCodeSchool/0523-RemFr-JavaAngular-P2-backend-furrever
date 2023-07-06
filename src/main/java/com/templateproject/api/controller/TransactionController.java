package com.templateproject.api.controller;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.templateproject.api.entity.Comment;
import com.templateproject.api.entity.Transaction;
import com.templateproject.api.repository.CommentRepository;
import com.templateproject.api.repository.TransactionRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/transactions")

public class TransactionController {

    private final TransactionRepository transactionRepo;
    private final CommentRepository commentRepo;

    public TransactionController(TransactionRepository transactionRepository, CommentRepository commentRepository) {
        this.transactionRepo = transactionRepository;
        this.commentRepo = commentRepository;
    }

    @PostMapping("/")
    public Transaction createTransaction(@RequestBody Transaction transaction){
        return this.transactionRepo.save(transaction);
    }

    @PutMapping("/{id}")
    public Transaction updateTransaction(@PathVariable UUID id, @RequestBody Transaction transactionToModify){
        if(id != null){
            Optional<Transaction> transactionOptional = this.transactionRepo.findById(id);

            if(transactionOptional.isPresent()){
                Transaction transactionToUpdate = transactionOptional.get();
                transactionToUpdate.setStatus(transactionToModify.getStatus());
                return this.transactionRepo.save(transactionToUpdate);
            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public void deleteTransaction (@PathVariable UUID id){
        if(id != null){
            Optional<Transaction> transactionOptional = this.transactionRepo.findById(id);

            if(transactionOptional.isPresent()){
                this.transactionRepo.deleteById(id);
            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/{id}/comments")
    public Comment createComment(@RequestBody Comment comment){
        return this.commentRepo.save(comment);
    }

    @PutMapping("/{id}/comments/{idComment}")
    public Comment updateComment(@PathVariable UUID id, @PathVariable UUID idComment, @RequestBody Comment commentToModify) {
        if (idComment != null && id != null) {
            Optional<Comment> commentOptional = this.commentRepo.findById(idComment);

            if(commentOptional.isPresent()){
                Comment commentToUpdate = commentOptional.get();
                commentToUpdate.setContent(commentToModify.getContent());
                return this.commentRepo.save(commentToUpdate);
            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}/comments/{idComment}")
    public void deleteComment(@PathVariable UUID idComment, @PathVariable UUID id){
        if(idComment != null && id != null){
            Optional<Comment> commentOptional = this.commentRepo.findById(idComment);

            if(commentOptional.isPresent()){
                this.commentRepo.deleteById(idComment);
            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
}