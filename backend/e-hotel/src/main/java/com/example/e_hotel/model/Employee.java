package com.example.e_hotel.model;

import jakarta.persistence.*;

@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="employeeid",nullable = false)
    private Integer employeeId;

    @Column(name ="hotelid",nullable = false)
    private Integer hotelId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String address;

    @Column(nullable = true)
    private String role;

    @Column(nullable = true)
    private String email;

    @Column(nullable = true)
    private String password;

    // Constructors
    public Employee() {}

    public Employee(Integer hotelId, String name, String address, String role, String email, String password) {
        this.hotelId = hotelId;
        this.name = name;
        this.address = address;
        this.role = role;
        this.email = email;
        this.password = password;
    }

    // Getters/Setters

    public Integer getEmployeeId() {
        return employeeId;
    }
    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public Integer getHotelId() {
        return hotelId;
    }
    public void setHotelId(Integer hotelId) {
        this.hotelId = hotelId;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
