package com.templateproject.api.controller;

import com.templateproject.api.dto.GetTansactionsResponse;
import com.templateproject.api.dto.TransactionUserTemplate;
import com.templateproject.api.entity.Comment;
import com.templateproject.api.entity.Transaction;
import com.templateproject.api.entity.User;
import com.templateproject.api.repository.CommentRepository;
import com.templateproject.api.repository.TransactionRepository;
import com.templateproject.api.repository.UserRepository;
import com.templateproject.api.service.utils.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    private final TransactionRepository transactionRepo;
    private final CommentRepository commentRepo;
    private final UserRepository userRepo;

    public TransactionController(TransactionRepository transactionRepository, CommentRepository commentRepository, UserRepository userRepo) {
        this.transactionRepo = transactionRepository;
        this.commentRepo = commentRepository;
        this.userRepo = userRepo;
    }

    @GetMapping("")
    public GetTansactionsResponse getTransaction(Principal principal) {
        String userName = principal.getName();
        User user = this.userRepo
                .findByEmail(userName)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Votre utilisateur n'a pas été trouvé."));
        GetTansactionsResponse getTansactionsResponse = new GetTansactionsResponse();
        getTansactionsResponse.setPetsitter(user.getIsPetSitter());
        if (user.getIsPetSitter()) {
            List<TransactionUserTemplate> transactionForPetsitter = this.transactionRepo.getTransactionsForPetsitter(user.getId());
            if (transactionForPetsitter.size() > 0) {
                getTansactionsResponse.setTransactionForPetsitter(transactionForPetsitter);
            }
        }
        List<TransactionUserTemplate> transactionFromUser = this.transactionRepo.getTransactionsByUser(user.getId());
        if (transactionFromUser.size() > 0) {
            getTansactionsResponse.setTransactionFromUser(transactionFromUser);
            for (TransactionUserTemplate transactionUserTemplate : transactionFromUser){
                Comment comment = this.commentRepo.getCommentByTransactionId(transactionUserTemplate.getIdTransaction());
                if (comment != null){
                    transactionUserTemplate.setCommentedTransaction(true);
                }
            }
        }
        return getTansactionsResponse;
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
    public Transaction updateTransaction(@PathVariable UUID transactionId, @RequestBody Boolean validation, Principal principal) {
        User user = this.userRepo
                .findByEmail(principal.getName())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Votre utilisateur n'a pas été trouvé."));
        Transaction transaction = this.transactionRepo
                .findById(transactionId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cette transaction n'a pas été mise à jour"));
        if (validation != null && user.getId() == transaction.getService().getUser().getId()) {
            transaction.setStatus(validation);
            return this.transactionRepo.save(transaction);
        } else {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Vous n'avez pas l'autorisation de modifier cette transaction.");
        }
    }

    @DeleteMapping("/{transactionId}")
    public void deleteTransaction(@PathVariable String transactionId, Principal principal) {
        UUID transactionUUID = UUID.fromString(transactionId);
        User user = this.userRepo
                .findByEmail(principal.getName())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Votre utilisateur n'a pas été trouvé."));
        Transaction transaction = this.transactionRepo
                .findById(transactionUUID)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cette transaction n'a pas été supprimée."));
        if (transaction.getStatus() == null && transaction.getUser().getId() == user.getId()) {
            this.transactionRepo.deleteById(transactionUUID);
        } else {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Vous n'avez pas l'autorisation de supprimer cette transaction.");
        }
    }

    @PostMapping("/{transactionId}/comments")
    @ResponseStatus(HttpStatus.CREATED)
    public Comment createComment(@RequestBody Comment comment, @PathVariable UUID transactionId){
        Transaction transaction = this.transactionRepo
                .findById(transactionId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cette transaction n'a pas été trouvée."));
        comment.setTransaction(transaction);
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