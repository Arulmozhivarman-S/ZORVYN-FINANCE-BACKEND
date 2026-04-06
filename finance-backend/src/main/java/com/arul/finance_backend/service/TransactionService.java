package com.arul.finance_backend.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.arul.finance_backend.enums.UserRole;
import com.arul.finance_backend.exceptions.InvalidAccessException;
import com.arul.finance_backend.exceptions.ResourceNotFoundException;
import com.arul.finance_backend.model.Transactions;
import com.arul.finance_backend.model.User;
import com.arul.finance_backend.repository.TransactionsRepository;
import com.arul.finance_backend.repository.UserRepository;

@Service
public class TransactionService {
    
    @Autowired
    TransactionsRepository transactionsRepository;
    UserRepository userRepository;


    public String addTransaction(Long userId, Transactions transactions){

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User Not Found"));

        
        if(user.getUserRole() != UserRole.ADMIN){
            throw new InvalidAccessException("Invalid role to add transaction");
        }

        transactions.setUser(user);
        transactions.setCreatedAt(LocalDateTime.now());

        transactionsRepository.save(transactions);
        return "Transaction added successfully";
    }

    public Page<Transactions> getUserTransactions(Long userId, int page, int size){

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User Not Found"));

        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());

        if(user.getUserRole() == UserRole.ADMIN){
            return transactionsRepository.findAll(pageable);
        }

        return transactionsRepository.findByUserUserId(userId, pageable);
    }

   public Transactions updateTransaction(Long transactionId, Transactions updated, Long userId) {

        Transactions existing = transactionsRepository.findById(transactionId)
                .orElseThrow(() -> new ResourceNotFoundException("Transaction not found"));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User Not Found"));

       
        if(user.getUserRole() != UserRole.ADMIN &&
        !existing.getUser().getUserId().equals(userId)){
            throw new InvalidAccessException("You are not allowed to update this transaction");
        }

        existing.setAmount(updated.getAmount());
        existing.setCategory(updated.getCategory());
        existing.setTransactionType(updated.getTransactionType());
        existing.setDescription(updated.getDescription());

        return transactionsRepository.save(existing);
    }

    public String deleteTransaction(Long transactionId, Long userId){

        Transactions existing = transactionsRepository.findById(transactionId)
                .orElseThrow(() -> new ResourceNotFoundException("Transaction Not Found"));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User Not Found"));

        if(user.getUserRole() != UserRole.ADMIN &&
        !existing.getUser().getUserId().equals(userId)){
            throw new InvalidAccessException("You are not allowed to delete this transaction");
        }

        transactionsRepository.deleteById(transactionId);
        return "Transaction Deleted Successfully";
    }
    
}
