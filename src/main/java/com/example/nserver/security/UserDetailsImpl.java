package com.example.nserver.security;

import com.example.nserver.model.User;
import com.example.nserver.repository.UserRepo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsImpl implements UserDetailsService {

    private UserRepo userRepo;

    public UserDetailsImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user= userRepo.findUserByEmail(email).orElseThrow(()->new RuntimeException("User did not exists!!"));
        return user;
    }
}
