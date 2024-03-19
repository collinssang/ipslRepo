package com.ipsl.taskmanagement.controller;

import com.ipsl.taskmanagement.helpers.ErrorResponses;
import com.ipsl.taskmanagement.model.User;
import com.ipsl.taskmanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")

public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<?> login(@RequestBody User user) {
        User user1 = userService.login(user.getUsername(), user.getPassword());
        if (user1 != null) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(user1);
        }else{
            ErrorResponses errorResponses = new ErrorResponses(HttpStatus.UNAUTHORIZED.value(), "Wrong Credentials Provided");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponses);
        }
    }
}
