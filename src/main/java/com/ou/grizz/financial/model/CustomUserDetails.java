package com.ou.grizz.financial.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

//This class represents the authenticated user. Note that the user is authenticated in the CustomUserDetailsService class
public class CustomUserDetails implements UserDetails {

    /*
    How we will get the user so that we can apply the below methods to it?
    lets make a User field, then make a constructor for it.
    How will we get this user object? if it is present in the database, the CustomUserDetailsService class will pass it to the constructor below
    */
    private User user;

    public CustomUserDetails(User user) {
        this.user = user;
    }
    //Spring Security will invoke the below methods in this class during the authentication process.
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null; //I will not implement roles into the application
    }
    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail(); //the username will be the user email in this application
    }

    public Double getUserBudget() {
        return user.getBudget();
    }

    public List<Expense> userExpenses() {
        return user.getExpenses();
    }

    public String userFirstName() {
        return user.getFirstName();
    }

    public String userLastName() {
        return user.getLastName();
    }

    public void setBudget(Double budget) {
        user.setBudget(budget);
    }

    public String getFullName() {
        return user.getFirstName() + " " + user.getLastName();
    }

    public Long getUserId(){return user.getId();}

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
