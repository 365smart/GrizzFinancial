package com.ou.grizz.financial.service;

import com.ou.grizz.financial.model.CustomUserDetails;
import com.ou.grizz.financial.model.User;
import com.ou.grizz.financial.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public void saveUser(User user) {

        /*
        here we use BCryptPasswordEncoder to encode the user’s password so the password itself
        is not stored in database (for better security) – only the hash value of the password is stored.
         */
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword); //encode the password before saving the user to the database
        userRepository.save(user);
    }

    @Override
    public List<User> listUsers() {
        return userRepository.findAll();
    }

    @Override
    public User findUserById(Long userId) {
        Optional<User> optional = userRepository.findById(userId);

        if (optional.isEmpty()){throw new UsernameNotFoundException("User Not Found");}
        User user = optional.get();
        return user;
    }
}
