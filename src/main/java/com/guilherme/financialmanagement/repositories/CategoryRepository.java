package com.guilherme.financialmanagement.repositories;

import com.guilherme.financialmanagement.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
