package com.expensemanager.restapi.repos;

import com.expensemanager.restapi.entity.ExpensesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ExpensesRepos extends JpaRepository<ExpensesEntity, Long> {
    Optional<ExpensesEntity> findByExpensesId(String expenseId);
}
