package com.example.e_hotel.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "renting")
public class Renting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="rentid",nullable = false)
    private Integer rentId;

    @Column(name ="customerid",nullable = false)
    private Integer customerId;

    @Column(name ="roomid",nullable = false)
    private Integer roomId;

    @Column(name ="startdate",nullable = false)
    private LocalDate startDate;

    @Column(name ="enddate",nullable = false)
    private LocalDate endDate;

    @Column(nullable = false)
    private Double payment;

    // Constructors
    public Renting() {}

    public Renting(Integer customerId, Integer roomId, LocalDate startDate, LocalDate endDate, Double payment) {
        this.customerId = customerId;
        this.roomId = roomId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.payment = payment;
    }

    // Getters and Setters
    public Integer getRentId() {
        return rentId;
    }
    public void setRentId(Integer rentId) {
        this.rentId = rentId;
    }

    public Integer getCustomerId() {
        return customerId;
    }
    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getRoomId() {
        return roomId;
    }
    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }
    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Double getPayment() {
        return payment;
    }
    public void setPayment(Double payment) {
        this.payment = payment;
    }
}
