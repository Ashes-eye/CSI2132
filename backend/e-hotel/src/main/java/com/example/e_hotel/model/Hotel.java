package com.example.e_hotel.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Hotel")
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int hotelID;
    private String email;
    private String phoneNumber;
    private String address;
    private int numberOfRooms;
    private String rating;

    public int getHotelID() { 
        return hotelID; 
    }
    public void setHotelID(int hotelID) {
        this.hotelID = hotelID; 
    }
    public String getEmail() {
        return email; 
    }
    public void setEmail(String email) { 
        this.email = email; 
    }
    public String getPhoneNumber() { 
        return phoneNumber; 
    }
    public void setPhoneNumber(String phoneNumber) { 
        this.phoneNumber = phoneNumber; 
    }
    public String getAddress() { 
        return address; 
    }
    public void setAddress(String address) { 
        this.address = address; 
    }
    public int getNumberOfRooms() { 
        return numberOfRooms; 
    }
    public void setNumberOfRooms(int numberOfRooms) { 
        this.numberOfRooms = numberOfRooms; 
    }
    public String getRating() { 
        return rating; 
    }
    public void setRating(String rating) { 
        this.rating = rating; 
    }
}
