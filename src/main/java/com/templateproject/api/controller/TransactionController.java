package com.templateproject.api.controller;

import com.templateproject.api.entity.Comment;
import com.templateproject.api.entity.Transaction;
import com.templateproject.api.entity.User;
import com.templateproject.api.repository.CommentRepository;
import com.templateproject.api.repository.ServiceRepository;
import com.templateproject.api.repository.TransactionRepository;
import com.templateproject.api.repository.UserRepository;
import com.templateproject.api.service.utils.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.UUID;

@RestController
@RequestMapping("/api/transactions")
@PreAuthorize("hasAnyAuthority('SCOPE_ROLE_USER', 'SCOPE_ROLE_PETSITTER')")
public class TransactionController {

    private final TransactionRepository transactionRepo;
    private final CommentRepository commentRepo;
    private final UserRepository userRepo;

    public TransactionController(TransactionRepository transactionRepository, CommentRepository commentRepository, UserRepository userRepo) {
        this.transactionRepo = transactionRepository;
        this.commentRepo = commentRepository;
        this.userRepo = userRepo;
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public Transaction createTransaction(@RequestBody Transaction transaction, Principal principal) {
        String userName = principal.getName();
        User user = this.userRepo
                .findByEmail(userName)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Votre utilisateur n'a pas été trouvé."));
        transaction.setUser(user);
        return this.transactionRepo.save(transaction);
    }

    @PutMapping("/{transactionId}")
    public Transaction updateTransaction(@PathVariable UUID transactionId, @RequestBody Transaction transactionToModify) {
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