package com.guilherme.financialmanagement.repositories;

import com.guilherme.financialmanagement.domain.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    @Query(value = "SELECT * FROM transactions WHERE account_id = :id", nativeQuery = true)
    List<Transaction> findTransactionsByAccountId(@Param("id") Long id);
}
