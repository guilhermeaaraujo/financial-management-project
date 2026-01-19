package com.guilherme.financialmanagement.repositories;

import com.guilherme.financialmanagement.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Long> {

    @Query(value = "SELECT * FROM accounts where user_id = :id", nativeQuery = true)
    List<Account> findByUserId(@Param("id") Long id);
}
