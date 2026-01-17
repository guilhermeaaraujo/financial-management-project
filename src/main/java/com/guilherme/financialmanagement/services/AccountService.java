package com.guilherme.financialmanagement.services;

import com.guilherme.financialmanagement.domain.Account;
import com.guilherme.financialmanagement.repositories.AccountRepository;
import com.guilherme.financialmanagement.services.exceptions.DatabaseException;
import com.guilherme.financialmanagement.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    public Account findById(Long id) {
        return accountRepository.findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException(id)
        );
    }

    public Account insert(Account Account) {
        return accountRepository.save(Account);
    }

    public void delete(Long id) {
        try {
            accountRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    public Account update(Long id, Account newAccount) {
        try {
            Account account = accountRepository.getReferenceById(id);
            account.setName(newAccount.getName());
            account.setType(newAccount.getType());
            return accountRepository.save(account);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }
}
