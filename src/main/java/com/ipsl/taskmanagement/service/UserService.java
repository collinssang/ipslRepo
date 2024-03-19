package com.ipsl.taskmanagement.service;

import com.ipsl.taskmanagement.model.User;

public interface UserService {

    User login(String username, String password);
}
