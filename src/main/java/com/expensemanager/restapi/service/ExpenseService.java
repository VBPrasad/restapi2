package com.expensemanager.restapi.service;

import com.expensemanager.restapi.dto.ExpenseDTO;

import java.util.List;

public interface ExpenseService {
     List<ExpenseDTO>  getAllExpenseServices();
     ExpenseDTO getExpensesByExpensesId(String expensesId);
     void deleteExpenseByExpensesId(String expensesId);
     ExpenseDTO saveExpenseDetails(ExpenseDTO expenseDto);
     ExpenseDTO updateExpenseDetails(ExpenseDTO expenseDTO,String expensesId);
}
