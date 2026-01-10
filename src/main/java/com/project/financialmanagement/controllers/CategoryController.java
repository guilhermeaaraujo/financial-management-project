package com.project.financialmanagement.controllers;

import com.project.financialmanagement.domain.Category;
import com.project.financialmanagement.domain.User;
import com.project.financialmanagement.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/categories")
public class CategoryController {

    @Autowired
    private CategoryService service;

    @GetMapping
    public ResponseEntity<List<Category>> findAll() {
        List<Category> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value="/{id}")
    public ResponseEntity<Category> findById(@PathVariable Long id) {
        Category category = service.findById(id);
        return ResponseEntity.ok().body(category);
    }

    @PostMapping
    public ResponseEntity<Category> insert(@RequestBody Category category) {
        category = service.insert(category);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(category).toUri();
        return ResponseEntity.created(uri).body(category);
    }

    // Sem métodos update e delete pq não há necessidade nessa entidade.
}
