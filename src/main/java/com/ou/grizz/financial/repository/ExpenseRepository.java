package com.ou.grizz.financial.repository;

import com.ou.grizz.financial.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    //find the expenses based on user email
    @Query(
            value = "SELECT * FROM expenses",
            nativeQuery = true
    )
    List<Expense> findAllExpenses();

    @Query(
            value = "SELECT * FROM expenses WHERE expenses.user_id = ?1",
            nativeQuery = true
    )
    List<Expense> findExpensesByUserid(Long userId);
}
