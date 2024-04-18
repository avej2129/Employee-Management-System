package com.employees.application.controller;

import com.employees.application.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Controller {

    @Autowired
    EmployeeService employeeService;

    @GetMapping("/hi")
    public String hello(){
        return"hello" ;
    }
    @GetMapping("/hello")
    public String HelloWorld(){
        return "hello World";
    }
    @GetMapping("/data")
   public ResponseEntity<List<String>> getPhoneNumber(){
        return ResponseEntity.ok(employeeService.getAllPhoneNumber());
    }

}
