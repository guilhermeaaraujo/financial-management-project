package com.project.financialmanagement.services;

import com.project.financialmanagement.domain.Account;
import com.project.financialmanagement.domain.User;
import com.project.financialmanagement.repositories.AccountRepository;
import com.project.financialmanagement.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    private AccountRepository repository;

    @Autowired
    private UserRepository userRepository;

    public ResponseEntity<List<Account>> findAll() {
        List<Account> list = repository.findAll();
        return ResponseEntity.ok().body(list);
    }

    public ResponseEntity<List<Account>> findByUserId(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        List<Account> accounts = repository.findByUserId(user.get().getId());
        return ResponseEntity.ok().body(accounts);
    }
}
