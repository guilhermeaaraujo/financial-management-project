package com.project.financialmanagement.controllers;

import com.project.financialmanagement.domain.Transaction;
import com.project.financialmanagement.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/transactions")
public class TransactionController {

    @Autowired
    private TransactionService service;

    @GetMapping
    public ResponseEntity<List<Transaction>> findAll() {
        List<Transaction> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value="/{id}")
    public ResponseEntity<Transaction> findById(@PathVariable Long id) {
        Transaction transaction = service.findById(id);
        return ResponseEntity.ok().body(transaction);
    }

    @PostMapping
    public ResponseEntity<Transaction> insert(@RequestBody Transaction transaction) {
        transaction = service.insert(transaction);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(transaction.getId()).toUri();
        return ResponseEntity.created(uri).body(transaction);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    // Não coloquei metódo uptade por que não há necessidade de atualizar uma transação
}
