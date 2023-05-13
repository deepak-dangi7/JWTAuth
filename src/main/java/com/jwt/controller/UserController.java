package com.jwt.controller;

import com.jwt.entity.User;
import com.jwt.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@Slf4j
@EnableMethodSecurity
public class UserController {

    @Autowired
    private UserService userService;
    @PostMapping("/create")
    public User save(@RequestBody User user){
        return userService.saveUser(user);
    }
    @GetMapping
    public List<User> get(){
        return userService.getAll();
    }
    @GetMapping("/admin")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String admin(){
        return "Only for admin";
    }
    @GetMapping("/normal")
    @PreAuthorize("hasAuthority('ROLE_NORMAL')")
    public String normal(){
        return "Only for normal";
    }
}
