package com.jwt.controller;


import com.jwt.dto.AuthRequest;
import com.jwt.jwt.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager manager;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private UserDetailsService userDetailsService;
    @PostMapping("/token")
    public String generateToken(@RequestBody AuthRequest request){
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(request.getUserName(),request.getPassword());
        Authentication authenticate = manager.authenticate(authenticationToken);
        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUserName());
        if (authenticate.isAuthenticated()) {
            return jwtTokenUtil.generateToken(userDetails);
        }else return "*** Unauthenticated access. ***";
    }
}