package com.employees.application.controller;

import com.employees.application.model.User;
import com.employees.application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/find")
  //  @PreAuthorize("hasAuthorize('ADMIN')")
    public List<User> findAllUser() {
        return userService.findAllUser();

    }

}