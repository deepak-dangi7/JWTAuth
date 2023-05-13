package com.jwt.service;

import com.jwt.entity.User;
import com.jwt.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepo repo;
    @Autowired
    private PasswordEncoder passwordEncoder;
    public User saveUser(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return repo.save(user);
    }
    public List<User> getAll(){
        return repo.findAll();
    }

}
