package com.example.e_hotel.model;

import jakarta.persistence.*;

@Entity
@Table(name = "hotel")
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hotelid", nullable = false)
    private Integer hotelId;

    @Column(name = "hotelchainid",nullable = false)
    private Integer hotelChainId;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(name ="phonenumber",nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    private String address;

    @Column(name ="numberofrooms",nullable = false)
    private Integer numberOfRooms;

    @Column(nullable = false)
    private int rating; // from 1 to 5

    // Constructors
    public Hotel() {}

    public Hotel(Integer hotelChainId, String email, String phoneNumber, String address, Integer numberOfRooms, int rating) {
        this.hotelChainId = hotelChainId;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.numberOfRooms = numberOfRooms;
        this.rating = rating;
    }

    // Getters and Setters
    public Integer getHotelId() {
        return hotelId;
    }
    public void setHotelId(Integer hotelId) {
        this.hotelId = hotelId;
    }

    public Integer getHotelChainId() {
        return hotelChainId;
    }
    public void setHotelChainId(Integer hotelChainId) {
        this.hotelChainId = hotelChainId;
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

    public Integer getNumberOfRooms() {
        return numberOfRooms;
    }
    public void setNumberOfRooms(Integer numberOfRooms) {
        this.numberOfRooms = numberOfRooms;
    }

    public int getRating() {
        return rating;
    }
    public void setRating(int rating) {
        this.rating = rating;
    }
}
