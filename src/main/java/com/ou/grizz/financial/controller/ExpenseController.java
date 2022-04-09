package com.ou.grizz.financial.controller;

import com.ou.grizz.financial.model.CustomUserDetails;
import com.ou.grizz.financial.model.Expense;
import com.ou.grizz.financial.model.User;
import com.ou.grizz.financial.service.ExpenseService;
import com.ou.grizz.financial.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
        //get the budget of the logged-in user
        Double loggedInUserBudget = loggedInUser.getUserBudget();
        //calculate how much did the logged-in user spend
        Double spent = 0.0;
        for (Expense expense : userExpenses) {
            spent += expense.getValue();
        }
        //calculate the logged-in user remaining balance and how much the user spent
        Double loggedInUserRemainingBalance = loggedInUserBudget - spent;
        Double loggedInUserTotalExpenses = spent;
        //bind the data to the model
        model.addAttribute("userExpenses", userExpenses);
        model.addAttribute("userBudget", loggedInUserBudget);
        model.addAttribute("remainingBalance", loggedInUserRemainingBalance);
        model.addAttribute("totalExpenses", loggedInUserTotalExpenses);
        return "expenses";
    }

    //method handler to show a form for the user to add a new expense
    @GetMapping("/showNewExpenseForm")
    public String showNewExpenseForm(Model model) {
        Expense expense = new Expense();
        model.addAttribute("expense",expense);
        return "new_expense";
    }

    @GetMapping("/showUpdateExpenseForm/{id}")
    public String showUpdateExpenseForm(@PathVariable("id")Long expenseId,Model model) {
        Expense expense = expenseService.findById(expenseId);
        model.addAttribute("expense",expense);
        return "update_expense";
    }

    //method handler to save the new expense the was created in the showNewExpenseForm
    @PostMapping("/saveExpense")
    public String saveExpense(@AuthenticationPrincipal CustomUserDetails loggedInUser,
                              @ModelAttribute("expense") Expense expense) {

        expenseService.saveExpense(loggedInUser.getUserId(), expense);
        return "redirect:/expenses";
    }

    @PostMapping("/updateExpense{id}")
    public String updateExpense(@PathVariable("id") Long expenseId,
                                @AuthenticationPrincipal CustomUserDetails loggedInUser,
                                @ModelAttribute("expense") Expense expense) {

        expenseService.updateExpense(loggedInUser.getUserId(), expenseId ,expense);
        return "redirect:/expenses";
    }

    @GetMapping("/deleteExpense/{id}")
    public String deleteExpense(@PathVariable(value = "id") Long expenseId, @AuthenticationPrincipal CustomUserDetails loggedInUser) {
        expenseService.deleteExpense(expenseId, loggedInUser.getUserId());
        return "redirect:/expenses";
    }

    //create a method to update the budget of the user
    @GetMapping("/showUpdateBudgetForm")
    public String showUpdateUserBudgetForum(@AuthenticationPrincipal CustomUserDetails loggedInUser, Model model) {
        String email = loggedInUser.getUsername(); //remember I used the email as username
        User user = userService.findUserByEmail(email);
        model.addAttribute("user", user);
        return "update_budget";
    }

    @PostMapping("/updateUserBudget")
    public String updateUserBudget(@AuthenticationPrincipal CustomUserDetails loggedInUser, @ModelAttribute("user") User updatedUser) {
        Double updatedBudget = updatedUser.getBudget();
        //save the new changes to the database
        userService.updateUserBudget(loggedInUser.getUserId(), updatedBudget);
        //show the updated data to the user
        loggedInUser.setBudget(updatedUser.getBudget());

        return "redirect:/expenses";
    }
}
