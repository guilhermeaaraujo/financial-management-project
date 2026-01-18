package com.guilherme.financialmanagement.controllers;

import com.guilherme.financialmanagement.domain.Transaction;
import com.guilherme.financialmanagement.domain.TransactionDTO;
import com.guilherme.financialmanagement.domain.dto.AccountDTO;
import com.guilherme.financialmanagement.domain.dto.UserDTO;
import com.guilherme.financialmanagement.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionService service;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<TransactionDTO>> findAll() {
        List<Transaction> transactions = service.findAll();
        List<TransactionDTO> transactionsDTO = new ArrayList<>();
        
        for (Transaction t : transactions) {
            transactionsDTO.add(new TransactionDTO(t.getId(), t.getValue(), t.getDate(), t.getAccount().getId(), t.getCategory()));
        }
        return ResponseEntity.ok().body(transactionsDTO);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<TransactionDTO> findById(@PathVariable Long id) {
        Transaction transaction = service.findById(id);
        TransactionDTO transactionDTO = new TransactionDTO(transaction.getId(), transaction.getValue(), transaction.getDate(), transaction.getAccount().getId(),transaction.getCategory());
        return ResponseEntity.ok().body(transactionDTO);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<TransactionDTO> insert(@RequestBody Transaction transaction) {
        service.insert(transaction);
        TransactionDTO transactionDTO = new TransactionDTO(transaction.getId(), transaction.getValue(), transaction.getDate(), transaction.getAccount().getId(),transaction.getCategory());
        return ResponseEntity.status(HttpStatus.CREATED).body(transactionDTO);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<TransactionDTO> update(@PathVariable Long id, @RequestBody Transaction transaction) {
        service.update(id, transaction);
        TransactionDTO transactionDTO = new TransactionDTO(transaction.getId(), transaction.getValue(), transaction.getDate(), transaction.getAccount().getId(),transaction.getCategory());
        return ResponseEntity.ok().body(transactionDTO);
    }

}
