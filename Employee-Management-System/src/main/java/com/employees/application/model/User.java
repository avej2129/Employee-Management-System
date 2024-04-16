package com.employees.application.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {
    @Id
    @Column(name = "emailId")
    private String username;
    private String password;
    private String roles;
//    private String firstName;
//    private String lastName;
//    private String phoneNumber;
}
