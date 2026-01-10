package com.project.financialmanagement.services;

import com.project.financialmanagement.domain.Account;
import com.project.financialmanagement.domain.Transaction;
import com.project.financialmanagement.repositories.AccountRepository;
import com.project.financialmanagement.repositories.TransactionRepository;
import com.project.financialmanagement.services.exceptions.DatabaseException;
import com.project.financialmanagement.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository repository;

    @Autowired
    private AccountRepository accountRepository;

    public List<Transaction> findAll() {
        return repository.findAll();
    }

    public Transaction findById(Long id) {
        Optional<Transaction> transaction = repository.findById(id);
        return transaction.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public List<Transaction> findByAccountId(Long accountId) {
        Account account = accountRepository.findById(accountId).orElseThrow(() -> new ResourceNotFoundException(accountId));
        List<Transaction> list = repository.findByAccountId(account.getId());
        return list;
    }

    public Transaction insert(Transaction transaction) {
        return repository.save(transaction);
    }

    public void delete(Long id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);
        }
    }
}
