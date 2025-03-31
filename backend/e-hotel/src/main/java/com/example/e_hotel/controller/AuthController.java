package com.example.e_hotel.controller;

import com.example.e_hotel.model.Customer;
import com.example.e_hotel.model.Employee;
import com.example.e_hotel.repository.CustomerRepository;
import com.example.e_hotel.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin
public class AuthController {

    @Autowired
    private CustomerRepository customerRepo;

    @Autowired
    private EmployeeRepository employeeRepo;

    // Customer Login
    @PostMapping("/customerLogin")
    public ResponseEntity<?> customerLogin(@RequestBody Map<String, String> body) {
        String email = body.get("email");
        String password = body.get("password");

        Customer c = customerRepo.findByEmail(email);
        if (c == null) {
            return ResponseEntity.status(404).body("No customer found with that email");
        }
        if (!c.getPassword().equals(password)) {
            return ResponseEntity.status(401).body("Invalid password");
        }
        return ResponseEntity.ok(c);
    }

    // Employee Login
    @PostMapping("/employeeLogin")
    public ResponseEntity<?> employeeLogin(@RequestBody Map<String, String> body) {
        String email = body.get("email");
        String password = body.get("password");

        System.out.println("EMAIL = " + email);
        System.out.println("PASSWORD = " + password);

        Employee e = employeeRepo.findByEmail(email);
        if (e == null) {
            return ResponseEntity.status(404).body("No employee found with that email");
        }

        if (!e.getPassword().equals(password)) {
            return ResponseEntity.status(401).body("Invalid password");
        }

        return ResponseEntity.ok(e);
    }



}
