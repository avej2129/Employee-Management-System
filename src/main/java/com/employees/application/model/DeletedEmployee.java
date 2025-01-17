package com.employees.application.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeletedEmployee {

    @Id
    private int id;

    private int employeeId;

    private String firstName;

    private String lastName;

    private String email;

    private String phoneNo;

}
