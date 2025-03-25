package com.example.e_hotel.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "Customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int customerID;
    
    private String fullName;
    private String address;
    private String idType; // e.g., SSN, SIN, Driver's License
    private LocalDate registrationDate;

    public int getCustomerID() { 
        return customerID; 
    }
    public void setCustomerID(int customerID) { 
        this.customerID = customerID; 
    }
    public String getFullName() { 
        return fullName; 
    }
    public void setFullName(String fullName) { 
        this.fullName = fullName; 
    }
    public String getAddress() { 
        return address; 
    }
    public void setAddress(String address) { 
        this.address = address; 
    }

    public String getIdType() { 
        return idType; 
    }
    public void setIdType(String idType) { 
        this.idType = idType; 
    }

    public LocalDate getRegistrationDate() { 
        return registrationDate; 
    }
    public void setRegistrationDate(LocalDate registrationDate) { 
        this.registrationDate = registrationDate; 
    }
}

