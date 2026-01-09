package com.project.financialmanagement.repositories;

import com.project.financialmanagement.domain.Account;
import com.project.financialmanagement.domain.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findByAccountId(Long id);
}

