package com.example.e_hotel.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "booking")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bookingid", nullable = false)
    private Integer bookingId;

    @Column(name = "customerid",nullable = false)
    private Integer customerId;

    @Column(name = "roomid",nullable = false)
    private Integer roomId;

    @Column(name="startdate",nullable = false)
    private LocalDate startDate;

    @Column(name = "enddate",nullable = false)
    private LocalDate endDate;

    @Column(nullable = false)
    private String status; // e.g. "CONFIRMED", "CANCELLED", "PENDING"

    @Column(name = "bookdate",nullable = false)
    private LocalDate bookDate;

    // Constructors
    public Booking() {}

    public Booking(Integer customerId, Integer roomId, LocalDate startDate, LocalDate endDate, String status, LocalDate bookDate) {
        this.customerId = customerId;
        this.roomId = roomId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.bookDate = bookDate;
    }

    // Getters and Setters
    public Integer getBookingId() {
        return bookingId;
    }
    public void setBookingId(Integer bookingId) {
        this.bookingId = bookingId;
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

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getBookDate() {
        return bookDate;
    }
    public void setBookDate(LocalDate bookDate) {
        this.bookDate = bookDate;
    }
}
