package com.guilherme.financialmanagement.services;


import com.guilherme.financialmanagement.domain.Account;
import com.guilherme.financialmanagement.domain.Category;
import com.guilherme.financialmanagement.domain.Transaction;
import com.guilherme.financialmanagement.domain.enums.CategoryType;
import com.guilherme.financialmanagement.repositories.AccountRepository;
import com.guilherme.financialmanagement.repositories.CategoryRepository;
import com.guilherme.financialmanagement.repositories.TransactionRepository;
import com.guilherme.financialmanagement.services.exceptions.DatabaseException;
import com.guilherme.financialmanagement.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Transaction> findAll() {
        return transactionRepository.findAll();
    }

    public Transaction findById(Long id) {
        return transactionRepository.findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException(id)
        );
    }

    public Transaction insert(Transaction transaction) {
        Long accountId = transaction.getAccount().getId();
        Account account = accountRepository.findById(accountId)
                .orElseThrow(
                        () -> new ResourceNotFoundException(accountId)
                );

        Category category = categoryRepository.getReferenceById(transaction.getCategory().getId());
        if (category.getType() == CategoryType.EXPENSE) {
            account.decreaseBalance(transaction.getValue());
        } else {
            account.increaseBalance(transaction.getValue());
        }

        return transactionRepository.save(transaction);
    }

    public void delete(Long id) {
        try {
            transactionRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    public Transaction update(Long id, Transaction newTransaction) {
        try {
            Transaction transaction = transactionRepository.getReferenceById(id);
            transaction.setDate(newTransaction.getDate());
            transaction.setCategory(newTransaction.getCategory());
            transaction.setValue(newTransaction.getValue());
            return transactionRepository.save(transaction);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }
}
