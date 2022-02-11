package com.ou.grizz.financial.controller;

import com.ou.grizz.financial.model.CustomUserDetails;
import com.ou.grizz.financial.model.Expense;
import com.ou.grizz.financial.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    @GetMapping("/expenses")
    public String userExpenses(@AuthenticationPrincipal CustomUserDetails userDetails, Model model) {
        List<Expense> userExpenses = expenseService.findAllExpensesByUserId(userDetails.getUserId()); //Remember userDetails represents the authenticated user
        model.addAttribute("userExpenses", userExpenses);
        return "expenses";
    }
}
