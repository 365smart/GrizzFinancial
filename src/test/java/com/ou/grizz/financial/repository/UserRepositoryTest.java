package com.ou.grizz.financial.repository;

import com.ou.grizz.financial.model.Expense;
import com.ou.grizz.financial.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@SpringBootTest
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Test
    public void saveExpenseForExistingUser() throws Exception {

        Optional<User> optional = userRepository.findById(9L);

        if (optional.isPresent()) {
            User user = optional.get();
            Expense expense = Expense.builder()
                    .description("Real Estate")
                    .value(1_250_000.0)
                    .date(LocalDate.of(2021, 1,8))
                    .user(user)
                    .build();
            user.addExpense(expense);
            userRepository.save(user);
        } else {
            throw new Exception("This user is not present");
        }
        /*
        Since the cascade type is "ALL" for the one to many relationship in the user class, any changes we do to the user will
        be applied to the expense. In other word, when we add a new expense for the user and we save the user. The new expense for the user will
        be saved in the database.
         */
    }

    @Test
    public void printAllUsers() {
        List<User> users = userRepository.findAll();
        System.out.println("users = " + users);
    }

    @Test
    public void findUserByEmail() {
        String email = "tom@gmail.com";
        User user = userRepository.findByEmail(email);
        System.out.println("user is " + user);
    }

}