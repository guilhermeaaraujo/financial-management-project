package com.guilherme.financialmanagement.controllers;

import com.guilherme.financialmanagement.domain.User;
import com.guilherme.financialmanagement.domain.dto.UserDTO;
import com.guilherme.financialmanagement.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService service;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<UserDTO>> findAll() {
        List<User> users = service.findAll();
        List<UserDTO> usersDTO = new ArrayList<>();

        for (User u : users) {
            usersDTO.add(new UserDTO(u.getId(), u.getName(), u.getEmail(), u.getRole()));
        }

        return ResponseEntity.ok().body(usersDTO);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable Long id) {
        User user = service.findById(id);
        UserDTO userDTO = new UserDTO(user.getId(), user.getName(), user.getEmail(), user.getRole());
        return ResponseEntity.ok().body(userDTO);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<UserDTO> insert(@RequestBody User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        service.insert(user);
        UserDTO userDTO = new UserDTO(user.getId(), user.getName(), user.getEmail(), user.getRole());
        return ResponseEntity.status(HttpStatus.CREATED).body(userDTO);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> update(@PathVariable Long id, @RequestBody User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        service.update(id, user);
        UserDTO userDTO = new UserDTO(user.getId(), user.getName(), user.getEmail(), user.getRole());
        return ResponseEntity.ok().body(userDTO);
    }

}
