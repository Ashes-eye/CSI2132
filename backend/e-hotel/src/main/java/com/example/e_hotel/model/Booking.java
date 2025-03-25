package com.example.e_hotel.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "Booking")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bookingID;

    private int customerID;
    private int roomID;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;

    public int getBookingID() { 
        return bookingID; 
    }
    public void setBookingID(int bookingID) { 
        this.bookingID = bookingID; 
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
}
