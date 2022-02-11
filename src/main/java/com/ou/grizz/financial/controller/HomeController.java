package com.ou.grizz.financial.controller;

import com.ou.grizz.financial.model.User;
import com.ou.grizz.financial.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

//This class is used to display the home page

@Controller
public class HomeController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String viewHomePage() {
        return "index";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User()); //we are creating an empty user object and biding it to variable called "user" to use it in thymeleaf
        return "signup_form";
    }

    @PostMapping("/process_register")
    public String processRegister(@ModelAttribute("user") User user) { //bind the user we created in the form with this user object
        userService.saveUser(user);
        return "register_success";
    }

    @GetMapping("/list_users")//this request is protected by spring security
    public String viewUserList(Model model) {
        List<User> listUsers = userService.listUsers();
        model.addAttribute("listUsers", listUsers); //Now, we can use the allUsersList in thymeleaf as "listUsers"
        return "users";
    }
}
