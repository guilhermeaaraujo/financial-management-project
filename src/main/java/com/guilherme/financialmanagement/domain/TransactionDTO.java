package com.guilherme.financialmanagement.domain;

import java.time.LocalDate;

public record TransactionDTO(Long id, Double value, LocalDate date, Long accountId, Category category) {
}
