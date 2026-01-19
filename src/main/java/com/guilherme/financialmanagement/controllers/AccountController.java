package com.guilherme.financialmanagement.controllers;

import com.guilherme.financialmanagement.domain.Account;
import com.guilherme.financialmanagement.domain.Transaction;
import com.guilherme.financialmanagement.domain.User;
import com.guilherme.financialmanagement.domain.dto.AccountDTO;
import com.guilherme.financialmanagement.domain.dto.TransactionDTO;
import com.guilherme.financialmanagement.domain.dto.UserDTO;
import com.guilherme.financialmanagement.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    private AccountService service;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<AccountDTO>> findAll() {
        List<Account> accounts = service.findAll();
        List<AccountDTO> accountsDTO = new ArrayList<>();

        for (Account a : accounts) {
            UserDTO userDTO = new UserDTO(a.getUser());
            accountsDTO.add(new AccountDTO(a, userDTO));
        }
        return ResponseEntity.ok().body(accountsDTO);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<AccountDTO> findById(@PathVariable Long id) {
        Account account = service.findById(id);
        UserDTO user = new UserDTO(account.getUser());
        AccountDTO accountDTO = new AccountDTO(account, user);
        return ResponseEntity.ok().body(accountDTO);
    }

    // Retorna as contas do usuário autenticado
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/myaccounts")
    public ResponseEntity<List<AccountDTO>> findAuthenticadedUserAccounts() {
        List<Account> accounts = service.findAuthenticadedUserAccounts();
        List<AccountDTO> accountsDTO = new ArrayList<>();

        for (Account a : accounts) {
            accountsDTO.add(new AccountDTO(a, new UserDTO(a.getUser())));
        }
        return ResponseEntity.ok().body(accountsDTO);
    }

    // Retorna as transações de uma conta
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/{id}/transactions")
    public ResponseEntity<List<TransactionDTO>> findAccountTransactions(@PathVariable Long id) {
        List<Transaction> transactions = service.findAccountTransactions(id);
        List<TransactionDTO> transactionsDTO = new ArrayList<>();

        for (Transaction t : transactions) {
            transactionsDTO.add(new TransactionDTO(t));
        }
        return ResponseEntity.ok().body(transactionsDTO);
    }

    // Usuários podem criar contas para si mesmos.
    @PreAuthorize("hasRole('USER')")
    @PostMapping
    public ResponseEntity<AccountDTO> insert(@RequestBody Account account) {
        service.insert(account);
        UserDTO user = new UserDTO(account.getUser());
        AccountDTO accountDTO = new AccountDTO(account, user);
        return ResponseEntity.status(HttpStatus.CREATED).body(accountDTO);
    }

    // Usuários podem deletar suas próprias contas.
    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<AccountDTO> update(@PathVariable Long id, @RequestBody Account account) {
        service.update(id, account);
        UserDTO user = new UserDTO(account.getUser());
        AccountDTO accountDTO = new AccountDTO(account, user);
        return ResponseEntity.ok().body(accountDTO);
    }

}
