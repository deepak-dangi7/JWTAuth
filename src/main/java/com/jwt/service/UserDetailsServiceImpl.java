package com.jwt.service;

import com.jwt.dto.CustomUserDetails;
import com.jwt.entity.User;
import com.jwt.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepo.findByName(username);
        return userOptional.map(CustomUserDetails::new).orElseThrow(
                ()->new RuntimeException("*** User with given name does not exist. ***"));
    }
}
