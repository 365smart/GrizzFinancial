package com.ou.grizz.financial.service;

import com.ou.grizz.financial.model.Expense;

import java.util.List;

public interface ExpenseService {

    //find all expenses by user id
    List<Expense> findAllExpensesByUserId(Long userId);

    //find expense by its id
    Expense findById(Long expenseId);

    //save a user's expense. So we need the user id, and the expense that we want to save
    void saveExpense(Long userId, Expense expense);

    //delete an expense
    void delete(Expense expense);

}
