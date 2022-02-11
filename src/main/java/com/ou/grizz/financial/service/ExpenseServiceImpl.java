package com.ou.grizz.financial.service;

import com.ou.grizz.financial.model.Expense;
import com.ou.grizz.financial.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//TODO implement these methods
@Service
public class ExpenseServiceImpl implements ExpenseService{


    @Autowired
    private ExpenseRepository expenseRepository;
    @Override
    public List<Expense> findAllExpensesByUserId(Long userId) {
        return expenseRepository.findExpensesByUserid(userId);
    }

    @Override
    public Expense findById(Long expenseId) {
        return null;
    }

    @Override
    public void saveExpense(Long userId, Expense expense) {

    }

    @Override
    public void delete(Expense expense) {

    }
}
