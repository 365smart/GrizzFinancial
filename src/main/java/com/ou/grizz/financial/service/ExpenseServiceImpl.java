package com.ou.grizz.financial.service;

import com.ou.grizz.financial.model.Expense;
import com.ou.grizz.financial.model.User;
import com.ou.grizz.financial.repository.ExpenseRepository;
import com.ou.grizz.financial.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

//TODO implement these methods
@Service
public class ExpenseServiceImpl implements ExpenseService{


    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired
    private UserRepository userRepository;

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

        Optional<User> optional = userRepository.findById(userId);
        if (optional.isPresent()) {
            User user = optional.get();
            expense.setUser(user);
            user.addExpense(expense);
            userRepository.save(user);
        } else {
            throw new UsernameNotFoundException("This user is not found");
        }

    }

    @Override
    public void delete(Expense expense) {

    }
}
