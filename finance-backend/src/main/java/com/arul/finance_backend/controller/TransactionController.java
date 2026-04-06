package com.arul.finance_backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.arul.finance_backend.model.Transactions;
import com.arul.finance_backend.service.TransactionService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;




@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @PostMapping("/{userId}")
    public ResponseEntity<?> addTransaction(
            @PathVariable Long userId,
            @Valid @RequestBody Transactions transactions) {

        transactionService.addTransaction(userId, transactions);
        return ResponseEntity.status(HttpStatus.CREATED).body("Transaction Added Successfully");
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Page<Transactions>> getUserTransactions(
            @PathVariable Long userId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        return ResponseEntity.ok(transactionService.getUserTransactions(userId, page, size));
    }

    @PutMapping("/{transactionId}")
    public ResponseEntity<Transactions> updateTransaction(
            @PathVariable Long transactionId,
            @RequestBody Transactions transaction, @RequestParam Long userId) {

        return ResponseEntity.ok(
                transactionService.updateTransaction(transactionId, transaction, userId)
        );
    }

    @DeleteMapping("/{transactionId}")
    public ResponseEntity<Void> deleteTransaction(@PathVariable Long transactionId,  @RequestParam Long userId) {
        transactionService.deleteTransaction(transactionId, userId);
        return ResponseEntity.noContent().build();
    }
    
}
