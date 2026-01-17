package com.guilherme.financialmanagement.controllers;

import com.guilherme.financialmanagement.domain.Account;
import com.guilherme.financialmanagement.domain.dto.AccountDTO;
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
            UserDTO userDTO = new UserDTO(a.getUser().getId(), a.getUser().getName(),
                    a.getUser().getEmail(), a.getUser().getRole());
            accountsDTO.add(new AccountDTO(a.getId(), a.getName(), a.getBalance(), a.getType(), userDTO));
        }
        return ResponseEntity.ok().body(accountsDTO);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<AccountDTO> findById(@PathVariable Long id) {
        Account account = service.findById(id);
        UserDTO user = new UserDTO(account.getUser().getId(), account.getUser().getName(),
                account.getUser().getEmail(), account.getUser().getRole());
        AccountDTO accountDTO = new AccountDTO(account.getId(), account.getName(), account.getBalance(), account.getType(), user);
        return ResponseEntity.ok().body(accountDTO);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<AccountDTO> insert(@RequestBody Account account) {
        service.insert(account);
        UserDTO user = new UserDTO(account.getUser().getId(), account.getUser().getName(),
                account.getUser().getEmail(), account.getUser().getRole());
        AccountDTO accountDTO = new AccountDTO(account.getId(), account.getName(), account.getBalance(), account.getType(), user);
        return ResponseEntity.status(HttpStatus.CREATED).body(accountDTO);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<AccountDTO> update(@PathVariable Long id, @RequestBody Account account) {
        service.update(id, account);
        UserDTO user = new UserDTO(account.getUser().getId(), account.getUser().getName(),
                account.getUser().getEmail(), account.getUser().getRole());
        AccountDTO accountDTO = new AccountDTO(account.getId(), account.getName(), account.getBalance(), account.getType(), user);
        return ResponseEntity.ok().body(accountDTO);
    }

}
