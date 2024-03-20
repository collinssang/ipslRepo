package com.ipsl.taskmanagement.service;

import com.ipsl.taskmanagement.model.User;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserService {

    User login(String username, String password);

    User createUser(User user);
    UserDetails loadUserByUsername(String username);
}
