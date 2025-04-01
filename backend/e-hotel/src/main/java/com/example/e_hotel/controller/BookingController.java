package com.example.e_hotel.controller;

import com.example.e_hotel.exceptions.ResourceNotFoundException;
import com.example.e_hotel.model.Booking;
import com.example.e_hotel.repository.BookingRepository;
import com.example.e_hotel.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/booking")
@CrossOrigin
public class BookingController {

    @Autowired
    private BookingRepository bookingRepo;

    @Autowired
    private RoomRepository roomRepo;

    @GetMapping
    public List<Booking> getAllBookings() {
        return bookingRepo.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Booking> getBookingById(@PathVariable Integer id) {
        Booking booking = bookingRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found with ID " + id));
        return ResponseEntity.ok(booking);
    }

    @PostMapping
    public ResponseEntity<?> createBooking(@RequestBody Booking booking) {

        System.out.println("Received booking request: " + booking);

        // Check if the room exists
        roomRepo.findById(booking.getRoomId())
                .orElseThrow(() -> new ResourceNotFoundException("Room not found with ID " + booking.getRoomId()));

        // Check if customer exists
        if (booking.getCustomerId() == null || booking.getStartDate() == null || booking.getEndDate() == null) {
            return ResponseEntity.badRequest().body("Missing booking info.");
        }

        if (!booking.getStartDate().isBefore(booking.getEndDate())) {
            return ResponseEntity.badRequest().body("End date must be after start date.");
        }

        // Check for existing bookings (room availability)
        List<Booking> existingBookings = bookingRepo.findByRoomId(booking.getRoomId());
        for (Booking b : existingBookings) {
            if (!"CANCELLED".equalsIgnoreCase(b.getStatus()) &&
                    !(booking.getEndDate().isBefore(b.getStartDate()) || booking.getStartDate().isAfter(b.getEndDate()))) {
                return ResponseEntity.badRequest().body("Room is already booked in that date range.");
            }
        }

        // Set bookDate to current date if not provided
        booking.setBookDate(LocalDate.now());
        if (booking.getStatus() == null || booking.getStatus().isBlank()) {
            booking.setStatus("transformed");
        }

        Booking saved = bookingRepo.save(booking);
        return ResponseEntity.ok(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateBooking(@PathVariable Integer id, @RequestBody Booking data) {
        System.out.println("Received update request for booking ID " + id + ": " + data);

        try {
            Booking existing = bookingRepo.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Booking not found with ID " + id));

            System.out.println("Found existing booking: " + existing);

            if (data.getCustomerId() != null) {
                existing.setCustomerId(data.getCustomerId());
            }
            if (data.getRoomId() != null) {
                existing.setRoomId(data.getRoomId());
            }
            if (data.getStartDate() != null) {
                existing.setStartDate(data.getStartDate());
            }
            if (data.getEndDate() != null) {
                existing.setEndDate(data.getEndDate());
            }
            if (data.getStatus() != null) {
                existing.setStatus(data.getStatus());
            }
            if (data.getBookDate() != null) {
                existing.setBookDate(data.getBookDate());
            }

            Booking saved = bookingRepo.save(existing);
            System.out.println("Booking updated successfully: " + saved);
            return ResponseEntity.ok(saved);
        } catch (Exception e) {
            System.err.println("Error updating booking: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Error updating booking: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBooking(@PathVariable Integer id) {
        if (!bookingRepo.existsById(id)) {
            throw new ResourceNotFoundException("Booking not found with ID " + id);
        }
        bookingRepo.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
