package com.project.financialmanagement.controllers;

import com.project.financialmanagement.domain.Account;
import com.project.financialmanagement.domain.Transaction;
import com.project.financialmanagement.services.AccountService;
import com.project.financialmanagement.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/accounts")
public class AccountController {

    @Autowired
    private AccountService service;

    @Autowired
    private TransactionService transactionService;

    @GetMapping
    public ResponseEntity<List<Account>> findAll() {
        List<Account> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Account> findById(@PathVariable Long id) {
        Account account = service.findById(id);
        return ResponseEntity.ok().body(account);
    }

    @GetMapping(value = "/{id}/transactions")
    public ResponseEntity<List<Transaction>> findTransactions(@PathVariable Long id) {
        List<Transaction> list = transactionService.findByAccountId(id);
        return ResponseEntity.ok().body(list);
    }

    @PostMapping
    public ResponseEntity<Account> insert(@RequestBody Account account) {
        account = service.insert(account);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(account.getId()).toUri();
        return ResponseEntity.created(uri).body(account);
    }

    @DeleteMapping(value="/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Account> update(@PathVariable Long id, @RequestBody Account account) {
        account = service.update(id, account);
        return ResponseEntity.ok().body(account);
    }
}
