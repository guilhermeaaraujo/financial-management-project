package com.project.financialmanagement.controllers;

import com.project.financialmanagement.domain.Transaction;
import com.project.financialmanagement.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/transactions")
public class TransactionController {

    @Autowired
    private TransactionService service;

    @GetMapping
    public ResponseEntity<List<Transaction>> findAll() {
        return service.findAll();
    }

    @GetMapping(value="/{id}")
    public ResponseEntity<Transaction> findById(@PathVariable Long id) {
        return service.findById(id);
    }
}
