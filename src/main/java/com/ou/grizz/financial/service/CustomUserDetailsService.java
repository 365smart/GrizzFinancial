package com.ou.grizz.financial.service;

import com.ou.grizz.financial.model.CustomUserDetails;
import com.ou.grizz.financial.model.User;
import com.ou.grizz.financial.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/*
Spring Security will invoke the loadUserByUsername() method to authenticate the user, and if successful, a new object of type CustomUserDetails object
is created to represent the authenticated user. This CustomUserDetails class has a constructor that needs a User object,
so we pass the user object that has just been authenticated. Now, once we pass the user object to the CustomUserDetails,
we will be able to utilize all the methods in the CustomUserDetails using this user that we passed.
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override //Note that UserDetials is an interface. So, we need to implement it
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException { //user name is email in this application

        User user = userRepository.findByEmail(email);

        if (user == null){throw new UsernameNotFoundException("User Not Found");}

        //pass the authenticated user as a CustomUserDetails object (this object is used to represent the authenticated user)
        return new CustomUserDetails(user);

        /*
        Everything we did so far: Implementing the UserDetailsService in the CustomUserDetailsService class
                                  Implementing the UserDetails in the CustomUserDetails class
                                  Creating the WebSecurityConfiguration class
            All of this was just for the authentication part. We have told Spring security to get the user and authenticate it from the database. That's it.
         */
    }
}
