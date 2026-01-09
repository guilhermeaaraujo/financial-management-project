package com.project.financialmanagement.services;

import com.project.financialmanagement.domain.User;
import com.project.financialmanagement.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public ResponseEntity<List<User>> findAll() {
        List<User> list = repository.findAll();
        return ResponseEntity.ok().body(list);
    }
}
