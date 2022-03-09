package com.ou.grizz.financial.service;

import com.ou.grizz.financial.model.User;

import java.util.List;

public interface UserService {

    //save the user to the data base
    void saveUser(User user);

    //return all users from the database
    List<User> listUsers();

    //find a user by their id
    User findUserById(Long userId);

    //find user by their email
    User findUserByEmail(String email);

    void updateUserBudget(Long userId, Double newBudget);


}
