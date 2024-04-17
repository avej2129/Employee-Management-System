package com.employees.application.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @GetMapping("/hi")
    public String hello(){
        return"hello" ;
    }
    @GetMapping("/hello")
    public String HelloWorld(){
        return "hello World";
    }
}
