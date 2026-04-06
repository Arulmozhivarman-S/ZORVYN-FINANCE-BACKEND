package com.arul.finance_backend.model;

import java.time.LocalDateTime;

import com.arul.finance_backend.enums.Category;
import com.arul.finance_backend.enums.TransactionType;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Transactions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transactionId;

    @Positive(message = "Amount must be greater than 0")
    private Double amount;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Transaction type is required")
    private TransactionType transactionType;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "category is required")
    private Category category;
    
    private LocalDateTime createdAt;
    private String description;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
