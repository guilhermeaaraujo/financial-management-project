package com.guilherme.financialmanagement.services;

import com.guilherme.financialmanagement.domain.Account;
import com.guilherme.financialmanagement.domain.Transaction;
import com.guilherme.financialmanagement.domain.User;
import com.guilherme.financialmanagement.repositories.AccountRepository;
import com.guilherme.financialmanagement.repositories.TransactionRepository;
import com.guilherme.financialmanagement.services.exceptions.DatabaseException;
import com.guilherme.financialmanagement.services.exceptions.ForbiddenOperationException;
import com.guilherme.financialmanagement.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    public Account findById(Long id) {
        return accountRepository.findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException(id)
        );
    }

    public List<Account> findAuthenticadedUserAccounts() {
        User user = getAuthenticadedUser();
        return accountRepository.findByUserId(user.getId());
    }

    // Regra de negócio: Usuários nao podem acessar transações de contas de outros usuários.
    public List<Transaction> findAccountTransactions(Long id) {
        Account account = accountRepository.getReferenceById(id);
        User authenticadedUser = getAuthenticadedUser();
        if (!account.getUser().getId().equals(authenticadedUser.getId())) {
            throw new ForbiddenOperationException("You cannot access transactions of another user!");
        }
        return transactionRepository.findTransactionsByAccountId(id);
    }

    // Regra de negócio: Usuários podem criar contas para si mesmos.
    public Account insert(Account account) {
        User authenticadedUser = getAuthenticadedUser();
        if (!account.getUser().getId().equals(authenticadedUser.getId())) {
            throw new ForbiddenOperationException("You cannot create an account for another user!");
        }
        return accountRepository.save(account);
    }

    // Regra de negócio: Usuários podem deletar suas próprias contas.
    public void delete(Long id) {
        try {
            Account account = accountRepository.findById(id)
                    .orElseThrow(
                            () -> new ResourceNotFoundException(id));
            User authenticadedUser = getAuthenticadedUser();
            if (!account.getUser().getId().equals(authenticadedUser.getId())) {
                throw new ForbiddenOperationException("You cannot delete an account that belongs to another user!");
            }
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

    public User getAuthenticadedUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return (User) auth.getPrincipal();
    }
}
