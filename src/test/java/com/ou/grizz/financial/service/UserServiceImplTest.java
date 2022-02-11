package com.ou.grizz.financial.service;

import com.ou.grizz.financial.model.User;
import com.ou.grizz.financial.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceImplTest {

    @Autowired
    UserRepository userRepository;

    @Test
    public void printUserById() {
        Optional<User> optional = userRepository.findById(1L);

        if (optional.isEmpty()){throw new UsernameNotFoundException("User Not Found");}

        User user = optional.get();
        System.out.println("User = " + user);
    }

}