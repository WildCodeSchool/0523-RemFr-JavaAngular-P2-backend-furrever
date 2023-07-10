package com.templateproject.api.controller;

import com.templateproject.api.entity.Comment;
import com.templateproject.api.entity.Transaction;
import com.templateproject.api.repository.CommentRepository;
import com.templateproject.api.repository.TransactionRepository;
import com.templateproject.api.service.utils.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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
    @ResponseStatus(HttpStatus.CREATED)
    public Transaction createTransaction(@RequestBody Transaction transaction) {
        return this.transactionRepo.save(transaction);
    }

    @PutMapping("/{transactionId}")
    public Transaction updateTransaction(@PathVariable UUID transactionId, @RequestBody Transaction transactionToModify) {
        //get current user  si petsitter ....
        //si owner alors verifier que le statut de la transation  === null

        Transaction transaction = this.transactionRepo
                .findById(transactionId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cette transaction n'a pas été mise à jour"));
        if ( /*current user == petsitter || */ transaction.getStatus() == null) {
            BeanUtils.copyNonNullProperties(transactionToModify, transaction);
            return this.transactionRepo.save(transaction);
        } else {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Vous n'avez pas l'autorisation de modifier cette transaction.");
        }
    }

    @DeleteMapping("/{transactionId}")
    public void deleteTransaction(@PathVariable UUID transactionId) {

        Transaction transaction = this.transactionRepo
                .findById(transactionId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cette transaction n'a pas été supprimée."));
        if (transaction.getStatus() == null) {
             this.transactionRepo.deleteById(transactionId);
        } else {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Vous n'avez pas l'autorisation de supprimer cette transaction.");
        }

    }

    @PostMapping("/{transactionId}/comments")
    @ResponseStatus(HttpStatus.CREATED)
    public Comment createComment(@RequestBody Comment comment) {
        return this.commentRepo.save(comment);
    }

    @PutMapping("/{transactionId}/comments/{commentId}")
    public Comment updateComment(@PathVariable UUID transactionId, @PathVariable UUID commentId, @RequestBody Comment commentToModify) {
        Transaction transaction = this.transactionRepo
                .findById(transactionId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cette transaction n'a pas été trouvée."));

        Comment comment = this.commentRepo
                .findById(commentId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Ce commentaire n'a pas été trouvé."));

        if (transaction.getComment().equals(comment)) {
            BeanUtils.copyNonNullProperties(commentToModify, comment);
            return this.commentRepo.save(comment);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Ce commentaire n'a pas été mis à jour.");
        }
    }

    @DeleteMapping("/{transactionId}/comments/{commentId}")
    public void deleteComment(@PathVariable UUID commentId, @PathVariable UUID transactionId) {
        Transaction transaction = this.transactionRepo
                .findById(transactionId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cette transaction n'a pas été trouvée."));

        Comment comment = this.commentRepo
                .findById(commentId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Ce commentaire n'a pas été trouvé."));

        if (transaction.getComment().equals(comment)) {
            this.commentRepo.deleteById(commentId);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Ce commentaire n'a pas été supprimé.");
        }
    }
}