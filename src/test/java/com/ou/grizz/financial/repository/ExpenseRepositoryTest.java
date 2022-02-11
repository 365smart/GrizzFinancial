package com.ou.grizz.financial.repository;

import com.ou.grizz.financial.model.Expense;
import com.ou.grizz.financial.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
class ExpenseRepositoryTest {

    @Autowired
    private ExpenseRepository expenseRepository;

    @Test
    public void saveExpenseForNewUser() {
        User user = User.builder()
                .firstName("John")
                .lastName("Cena")
                .budget(1_000_000.0)
                .password("JohnCena")
                .email("john@gmail.com")
                .build();

        Expense expense = Expense.builder()
                .description("house")
                .value(300_000.0)
                .date(LocalDate.of(2021,9,20))
                .user(user)
                .build();

        expenseRepository.save(expense);
        /*
        Since the cascade type is "ALL" for the many-to-one relationship in the Expense class, any changes we do to the expense will
        be applied to the corresponding user. In other word, when we add a new expense for a new user and we save that expense,
        a new user will be saved to the database.
         */
    }
    @Test
    public void printAllExpenses() {
        List<Expense> expenses = expenseRepository.findAllExpenses();
        System.out.println("expenses = " + expenses);
    }

    @Test
    public void printExpensesByUserid() {
        List<Expense> userExpenses = expenseRepository.findExpensesByUserid(1L);
        System.out.println("expenses = " + userExpenses);
    }

}