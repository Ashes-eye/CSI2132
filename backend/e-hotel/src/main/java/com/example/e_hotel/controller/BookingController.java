package com.example.e_hotel.controller;

import com.example.e_hotel.model.Booking;
import com.example.e_hotel.repository.BookingRepository;
import org.springframework.web.bind.annotation.*;

import com.example.e_hotel.dto.CheckInRequest;
import com.example.e_hotel.model.Renting;
import com.example.e_hotel.repository.RentingRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/bookings")
@CrossOrigin(origins = "*")
public class BookingController {

    private final BookingRepository bookingRepository;
    private final RentingRepository rentingRepository;

    public BookingController(BookingRepository bookingRepository, RentingRepository rentingRepository) {
        this.bookingRepository = bookingRepository;
        this.rentingRepository = rentingRepository;
    }

    // Get all bookings
    @GetMapping
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    // Get booking by ID
    @GetMapping("/{id}")
    public Optional<Booking> getBookingById(@PathVariable int id) {
        return bookingRepository.findById(id);
    }

    // Get bookings for a specific customer
    @GetMapping("/customer/{customerID}")
    public List<Booking> getBookingsByCustomerID(@PathVariable int customerID) {
        return bookingRepository.findByCustomerID(customerID);
    }

    // Create a new booking
    @PostMapping
    public Booking createBooking(@RequestBody Booking booking) {
        return bookingRepository.save(booking);
    }

    // Update an existing booking
    @PutMapping("/{id}")
    public Booking updateBooking(@PathVariable int id, @RequestBody Booking updatedBooking) {
        updatedBooking.setBookingID(id);
        return bookingRepository.save(updatedBooking);
    }

    // Delete a booking
    @DeleteMapping("/{id}")
    public void deleteBooking(@PathVariable int id) {
        bookingRepository.deleteById(id);
    }

    // Employee check-in: convert booking to renting
    @PostMapping("/checkin")
    public Renting checkInBooking(@RequestBody Renting renting) {
        // Optionally you can delete or mark the booking as fulfilled
        if (renting.getBookingID() != 0) {
            bookingRepository.deleteById(renting.getBookingID());
        }
        return rentingRepository.save(renting);
    }
}