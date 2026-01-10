package com.project.financialmanagement.services;

import com.project.financialmanagement.domain.Category;
import com.project.financialmanagement.repositories.CategoryRepository;
import com.project.financialmanagement.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repository;

    public List<Category> findAll() {
        return repository.findAll();
    }

    public Category findById(Long id) {
        Optional<Category> category = repository.findById(id);
        return category.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public Category insert(Category category) {
        return repository.save(category);
    }
}
