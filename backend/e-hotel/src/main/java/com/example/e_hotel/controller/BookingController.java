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
        // Check if the room exists
        roomRepo.findById(booking.getRoomId())
                .orElseThrow(() -> new ResourceNotFoundException("Room not found with ID " + booking.getRoomId()));

        LocalDate start = booking.getStartDate();
        LocalDate end = booking.getEndDate();
        List<Booking> existingBookings = bookingRepo.findByRoomId(booking.getRoomId());
        for (Booking b : existingBookings) {
            if (!(end.isBefore(b.getStartDate()) || start.isAfter(b.getEndDate()))) {
                return ResponseEntity.badRequest().body("Room is already booked in that date range!");
            }
        }

        // Set bookDate to current date
        booking.setBookDate(LocalDate.now());

        // Set default status
        //Not done
        if (booking.getStatus() == null || booking.getStatus().isBlank()) {
            booking.setStatus("PENDING");
        }

        Booking saved = bookingRepo.save(booking);
        return ResponseEntity.ok(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Booking> updateBooking(@PathVariable Integer id, @RequestBody Booking data) {
        Booking existing = bookingRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found with ID " + id));

        existing.setCustomerId(data.getCustomerId());
        existing.setRoomId(data.getRoomId());
        existing.setStartDate(data.getStartDate());
        existing.setEndDate(data.getEndDate());
        existing.setStatus(data.getStatus());
        existing.setBookDate(data.getBookDate()); // allow manual update, or you can disable this

        Booking saved = bookingRepo.save(existing);
        return ResponseEntity.ok(saved);
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
