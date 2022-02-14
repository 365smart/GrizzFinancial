package com.ou.grizz.financial.controller;

import com.ou.grizz.financial.model.CustomUserDetails;
import com.ou.grizz.financial.model.Expense;
import com.ou.grizz.financial.service.ExpenseService;
import com.ou.grizz.financial.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    @Autowired
    private UserService userService;

    @GetMapping("/expenses")
    public String userExpenses(@AuthenticationPrincipal CustomUserDetails loggedInUser, Model model) {
        List<Expense> userExpenses = expenseService.findAllExpensesByUserId(loggedInUser.getUserId()); //Remember userDetails represents the authenticated user
        model.addAttribute("userExpenses", userExpenses);
        return "expenses";
    }

    //method handler to show a form for the user to add a new expense
    @GetMapping("/showNewExpenseForm")
    public String showNewExpenseForm(Model model) {
        Expense expense = new Expense();
        model.addAttribute("expense",expense);
        return "new_expense";
    }

    //method handler to save the new expense the was created in the showNewExpenseForm
    @PostMapping("/saveExpense")
    public String saveExpense(@AuthenticationPrincipal CustomUserDetails loggedInUser,
                              @ModelAttribute("expense") Expense expense) {

        expenseService.saveExpense(loggedInUser.getUserId(), expense);
        return "redirect:/expenses";
    }
}
