package com.guilherme.financialmanagement.services;


import com.guilherme.financialmanagement.domain.Account;
import com.guilherme.financialmanagement.domain.Category;
import com.guilherme.financialmanagement.domain.Transaction;
import com.guilherme.financialmanagement.domain.User;
import com.guilherme.financialmanagement.domain.enums.CategoryType;
import com.guilherme.financialmanagement.repositories.AccountRepository;
import com.guilherme.financialmanagement.repositories.CategoryRepository;
import com.guilherme.financialmanagement.repositories.TransactionRepository;
import com.guilherme.financialmanagement.services.exceptions.DatabaseException;
import com.guilherme.financialmanagement.services.exceptions.ForbiddenOperationException;
import com.guilherme.financialmanagement.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

    // Regra de negócio: Usuários podem inserir transações somente em suas próprias contas!
    public Transaction insert(Transaction transaction) {
        Long accountId = transaction.getAccount().getId();
        Account account = accountRepository.findById(accountId)
                .orElseThrow(
                        () -> new ResourceNotFoundException(accountId)
                );

        User authenticadedUser = getAuthenticadedUser();

        if (!account.getUser().getId().equals(authenticadedUser.getId())) {
            throw new ForbiddenOperationException("You cannot add transactions in an account that does not belong to you!");
        }

        Category category = categoryRepository.getReferenceById(transaction.getCategory().getId());
        if (category.getType() == CategoryType.EXPENSE) {
            account.decreaseBalance(transaction.getValue());
        } else {
            account.increaseBalance(transaction.getValue());
        }

        return transactionRepository.save(transaction);
    }

    // Regra de negócio: Usuários podem deletar transações somente em suas próprias contas!
    public void delete(Long id) {
        try {
            Transaction transaction = transactionRepository.getReferenceById(id);
            Long accountId = transaction.getAccount().getId();
            Account account = accountRepository.findById(accountId)
                    .orElseThrow(
                            () -> new ResourceNotFoundException(accountId)
                    );

            User authenticadedUser = getAuthenticadedUser();

            if (!account.getUser().getId().equals(authenticadedUser.getId())) {
                throw new ForbiddenOperationException("You cannot delete transactions in an account that does not belong to you!");
            }
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

    public User getAuthenticadedUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return (User) auth.getPrincipal();
    }
}
