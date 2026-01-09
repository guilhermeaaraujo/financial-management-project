package com.project.financialmanagement.controllers;

import com.project.financialmanagement.domain.Account;
import com.project.financialmanagement.domain.User;
import com.project.financialmanagement.services.AccountService;
import com.project.financialmanagement.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AccountService accountService;

    @GetMapping
    public ResponseEntity<List<User>> findAll() {
        return userService.findAll();
    }

    @GetMapping(value="/{id}")
    public ResponseEntity<User> findById(@PathVariable Long id) {
        return userService.findById(id);
    }

    @GetMapping(value = "/{id}/accounts")
    public ResponseEntity<List<Account>> findAccounts(@PathVariable Long id) {
        return accountService.findByUserId(id);
    }
}
