package com.expensemanager.restapi.repos;

import com.expensemanager.restapi.entity.ExpensesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpensesRepos extends JpaRepository<ExpensesEntity, Long> {
}
