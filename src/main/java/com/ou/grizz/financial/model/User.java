package com.ou.grizz.financial.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "expenses") //without this, the program crashes.
                                // There is no expense column in the user entity in the DB. So, when printing user, do not print expense details
@Builder
@Entity
@Table(name = "users")
public class User implements Serializable {

    @Id
    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_sequence"
    )
    private Long id;

    @Column(
            name = "email",
            nullable = false,
            unique = true,
            length = 45 //max length for email
    )
    private String email;

    @Column(
            name = "first_name",
            nullable = false,
            length = 20 //max length for first name
    )
    private String firstName;

    @Column(
            name = "last_name",
            nullable = false,
            length = 20 //max length for last name
    )
    private String lastName;

    @Column(
            name = "password",
            nullable = false,
            length = 64 //max length for password
    )
    private String password;

    @Column(name = "budget", nullable = false)
    private Double budget;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Expense> expenses;

    public void addExpense(Expense expense) {
        if (expenses == null) expenses = new ArrayList<>();
        expenses.add(expense);
    }

    public void updateExpense(Expense expense) {
        expenses.remove(expense);
        expenses.add(expense);
    }

    public void removeExpense(Expense expense) {
        expenses.remove(expense);
    }


}
