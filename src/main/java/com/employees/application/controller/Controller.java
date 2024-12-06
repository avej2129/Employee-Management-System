package com.employees.application.controller;

import com.employees.application.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api")
public class Controller {

    @Autowired
    EmployeeService employeeService;

    @GetMapping("/data")
    public ResponseEntity<List<String>> getPhoneNumber() {
        return ResponseEntity.ok(employeeService.getAllPhoneNumber());
    }

    @GetMapping("/username")
    public String getUserName(Principal principal) {
        return principal.getName().toUpperCase();
    }
}
