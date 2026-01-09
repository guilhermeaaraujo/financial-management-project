package com.project.financialmanagement.services;

import com.project.financialmanagement.domain.Category;
import com.project.financialmanagement.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repository;

    public ResponseEntity<List<Category>> findAll() {
        List<Category> list = repository.findAll();
        return ResponseEntity.ok().body(list);
    }

    public ResponseEntity<Category> findById(Long id) {
        Optional<Category> Category = repository.findById(id);
        return ResponseEntity.ok().body(Category.get());
    }
}
