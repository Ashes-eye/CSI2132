package com.example.e_hotel.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "Renting")
public class Renting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int rentingID;

    private int customerID;
    private int roomID;
    private int employeeID;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private double paymentAmount;

    public int getRentingID() { 
        return rentingID; 
    }
    public void setRentingID(int rentingID) { 
        this.rentingID = rentingID; 
    }
    public int getCustomerID() { 
        return customerID; 
    }
    public void setCustomerID(int customerID) { 
        this.customerID = customerID; 
    }
    public int getRoomID() { 
        return roomID; 
    }
    public void setRoomID(int roomID) { 
        this.roomID = roomID; 
    }
    public int getEmployeeID() { 
        return employeeID; 
    }
    public void setEmployeeID(int employeeID) { 
        this.employeeID = employeeID; 
    }
    public LocalDate getCheckInDate() { 
        return checkInDate; 
    }
    public void setCheckInDate(LocalDate checkInDate) { 
        this.checkInDate = checkInDate; 
    }
    public LocalDate getCheckOutDate() { 
        return checkOutDate; 
    }
    public void setCheckOutDate(LocalDate checkOutDate) { 
        this.checkOutDate = checkOutDate; 
    }
    public double getPaymentAmount() { 
        return paymentAmount; 
    }
    public void setPaymentAmount(double paymentAmount) { 
        this.paymentAmount = paymentAmount; 
    }
}
