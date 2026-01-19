package com.guilherme.financialmanagement.domain.dto;

import com.guilherme.financialmanagement.domain.Category;
import com.guilherme.financialmanagement.domain.Transaction;

import java.time.LocalDate;

public record TransactionDTO(Long id, Double value, LocalDate date, Long accountId, Category category) {

    public TransactionDTO(Transaction transaction) {
        this(transaction.getId(), transaction.getValue(), transaction.getDate(), transaction.getAccount().getId(), transaction.getCategory());
    }
}
