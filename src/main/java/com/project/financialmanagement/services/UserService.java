package com.project.financialmanagement.services;

import com.project.financialmanagement.domain.User;
import com.project.financialmanagement.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.autoconfigure.WebMvcProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public ResponseEntity<List<User>> findAll() {
        List<User> list = repository.findAll();
        return ResponseEntity.ok().body(list);
    }

    public ResponseEntity<User> findById(Long id) {
        Optional<User> user = repository.findById(id);
        return ResponseEntity.ok().body(user.get());
    }

    public User insert(User user) {
        return repository.save(user);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public User update(Long id, User newUser) {
        User user = repository.getReferenceById(id);
        updateData(user, newUser);
        return repository.save(user);
    }

    private void updateData(User user, User newUser) {
        user.setName(newUser.getName());
        user.setEmail(newUser.getEmail());
    }
}
