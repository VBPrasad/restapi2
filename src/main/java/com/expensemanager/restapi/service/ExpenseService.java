package com.expensemanager.restapi.service;

import com.expensemanager.restapi.dto.ExpensesDto;

import java.util.List;

public interface ExpenseService {
     List<ExpensesDto>  getAllExpenseServices();
     ExpensesDto getExpensesByExpensesId(String expensesId);
     void deleteExpenseByExpensesId(String expensesId);
}
