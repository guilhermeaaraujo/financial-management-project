package com.project.financialmanagement.services;

import com.project.financialmanagement.domain.Account;
import com.project.financialmanagement.domain.User;
import com.project.financialmanagement.repositories.AccountRepository;
import com.project.financialmanagement.repositories.UserRepository;
import com.project.financialmanagement.services.exceptions.DatabaseException;
import com.project.financialmanagement.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    private AccountRepository repository;

    @Autowired
    private UserRepository userRepository;

    public List<Account> findAll() {
        return repository.findAll();
    }

    public Account findById(Long id) {
        Optional<Account> account = repository.findById(id);
        return account.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public List<Account> findByUserId(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException(userId));
        List<Account> list = repository.findByUserId(user.getId());
        return list;
    }

    public Account insert(Account account) {
        return repository.save(account);
    }

    public void delete(Long id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    public Account update(Long id, Account newAccount) {
        try {
            Account account = repository.getReferenceById(id);
            updateData(account, newAccount);
            return repository.save(account);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }

    private void updateData(Account account, Account newAccount) {
        account.setName(newAccount.getName());
        account.setType(newAccount.getType());
    }
}
