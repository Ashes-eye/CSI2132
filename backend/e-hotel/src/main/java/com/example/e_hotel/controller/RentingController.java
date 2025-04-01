package com.example.e_hotel.controller;

import com.example.e_hotel.exceptions.ResourceNotFoundException;
import com.example.e_hotel.model.Booking;
import com.example.e_hotel.model.Renting;
import com.example.e_hotel.model.Room;
import com.example.e_hotel.repository.BookingRepository;
import com.example.e_hotel.repository.RentingRepository;
import com.example.e_hotel.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.temporal.ChronoUnit;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/renting")
@CrossOrigin
public class RentingController {

    @Autowired
    private RentingRepository rentingRepo;

    @Autowired
    private BookingRepository bookingRepo;

    @Autowired
    private RoomRepository roomRepo;

    @GetMapping
    public List<Renting> getAllRentings() {
        return rentingRepo.findAll();
    }

    @PostMapping("/checkin")
    public ResponseEntity<?> checkIn(@RequestParam Integer bookingId) {
        // Fetch booking
        Booking booking = bookingRepo.findById(bookingId)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found with ID " + bookingId));

        Room room = roomRepo.findById(booking.getRoomId())
                .orElseThrow(() -> new ResourceNotFoundException("Room not found with ID " + booking.getRoomId()));

        // Calculate payment
        long days = ChronoUnit.DAYS.between(booking.getStartDate(), booking.getEndDate());
        if (days <= 0) days = 1; // Ensure at least 1-day payment
        double payment = days * room.getPrice();

        // Create Renting
        Renting rent = new Renting(
                booking.getCustomerId(),
                booking.getRoomId(),
                booking.getStartDate(),
                booking.getEndDate(),
                payment
        );

        Renting savedRent = rentingRepo.save(rent);

        // Delete booking
        bookingRepo.deleteById(bookingId);

        return ResponseEntity.ok(savedRent);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Renting> getRenting(@PathVariable Integer id) {
        Renting rent = rentingRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Renting not found with ID " + id));
        return ResponseEntity.ok(rent);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRenting(@PathVariable Integer id) {
        if (!rentingRepo.existsById(id)) {
            throw new ResourceNotFoundException("Renting not found with ID " + id);
        }
        rentingRepo.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
