package com.guilherme.financialmanagement.repositories;

import com.guilherme.financialmanagement.domain.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
