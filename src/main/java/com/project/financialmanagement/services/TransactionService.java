package com.project.financialmanagement.services;

import com.project.financialmanagement.domain.Account;
import com.project.financialmanagement.domain.Transaction;
import com.project.financialmanagement.domain.User;
import com.project.financialmanagement.repositories.AccountRepository;
import com.project.financialmanagement.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository repository;

    @Autowired
    private AccountRepository accountRepository;

    public ResponseEntity<List<Transaction>> findAll() {
        List<Transaction> list = repository.findAll();
        return ResponseEntity.ok().body(list);
    }

    public ResponseEntity<Transaction> findById(Long id) {
        Optional<Transaction> Transaction = repository.findById(id);
        return ResponseEntity.ok().body(Transaction.get());
    }

    public ResponseEntity<List<Transaction>> findByAccountId(Long accountId) {
        Optional<Account> account = accountRepository.findById(accountId);
        List<Transaction> transactions = repository.findByAccountId(account.get().getId());
        return ResponseEntity.ok().body(transactions);
    }
}
