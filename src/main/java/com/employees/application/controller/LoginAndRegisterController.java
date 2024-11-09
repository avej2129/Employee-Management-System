package com.employees.application.controller;

import com.employees.application.model.User;
import com.employees.application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api")
public class LoginAndRegisterController {
    @Autowired
    UserService userService;
    @GetMapping("/login")
    public String login(){
        return "login";
    }
    @GetMapping("/register")
    public String register(Model model){
        model.addAttribute("User", new User());
      //  model.addAttribute("successMessage","You are Register successfully");
        return "userRegister";
    }
    @PostMapping("/create")
    public String saveUser(User user){
         userService.saveUser(user);
        return "redirect:/api/login";
    }

}
