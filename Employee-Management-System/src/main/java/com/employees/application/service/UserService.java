package com.employees.application.service;

import com.employees.application.model.User;

import java.util.List;

public interface UserService {

    List<User> findAllUser();

    User SaveUser(User user);
}
