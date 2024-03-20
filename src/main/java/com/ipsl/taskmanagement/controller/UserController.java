package com.ipsl.taskmanagement.controller;

import com.ipsl.taskmanagement.dto.LoginResponse;
import com.ipsl.taskmanagement.helpers.ErrorResponses;
import com.ipsl.taskmanagement.model.User;
import com.ipsl.taskmanagement.service.TokenProvider;
import com.ipsl.taskmanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")

public class UserController {
    private final UserService userService;
    private final AuthenticationManager authenticationManager;


    @Autowired
    public UserController(UserService userService, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/create")
    @PreAuthorize("permitAll()")
    public ResponseEntity<?> createUser(@RequestBody User user) {
        String encryptedPassword = new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(encryptedPassword);
        User created = userService.createUser(user);
        if (created != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(created);
        } else {
            ErrorResponses errorResponses = new ErrorResponses(HttpStatus.BAD_REQUEST.value(), "Failed to create the user. please try again");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponses);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        UserDetails user1 = userService.loadUserByUsername(user.getUsername());
        if (user1 != null) {
            var usernamePassword = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
            authenticationManager.authenticate(usernamePassword);
            String token = TokenProvider.generateToken(user.getUsername());
            var response = new LoginResponse(user1, token);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
        } else {
            ErrorResponses errorResponses = new ErrorResponses(HttpStatus.UNAUTHORIZED.value(), "Wrong Credentials Provided");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponses);
        }
    }


}
