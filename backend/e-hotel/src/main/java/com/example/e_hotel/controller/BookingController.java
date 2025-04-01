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
<<<<<<< HEAD
        System.out.println("Received booking request: " + booking);
        
=======
>>>>>>> c23b03cee851e4a31fbf205b0a87f362dada3572
        // Check if the room exists
        roomRepo.findById(booking.getRoomId())
                .orElseThrow(() -> new ResourceNotFoundException("Room not found with ID " + booking.getRoomId()));

<<<<<<< HEAD
        // Check if customer exists
        if (booking.getCustomerId() == null) {
            return ResponseEntity.badRequest().body("Customer ID is required!");
        }

        // Validate dates
        LocalDate start = booking.getStartDate();
        LocalDate end = booking.getEndDate();
        
        if (start == null || end == null) {
            return ResponseEntity.badRequest().body("Start date and end date are required!");
        }

        if (start.isEqual(end) || start.isAfter(end)) {
            return ResponseEntity.badRequest().body("End date must be after start date!");
        }
        
        // Check for existing bookings (room availability)
        List<Booking> existingBookings = bookingRepo.findByRoomId(booking.getRoomId());
        for (Booking b : existingBookings) {
            // Skip cancelled bookings when checking availability
            if (b.getStatus() != null && b.getStatus().equalsIgnoreCase("CANCELLED")) {
                continue;
            }
            
=======
        LocalDate start = booking.getStartDate();
        LocalDate end = booking.getEndDate();
        List<Booking> existingBookings = bookingRepo.findByRoomId(booking.getRoomId());
        for (Booking b : existingBookings) {
>>>>>>> c23b03cee851e4a31fbf205b0a87f362dada3572
            if (!(end.isBefore(b.getStartDate()) || start.isAfter(b.getEndDate()))) {
                return ResponseEntity.badRequest().body("Room is already booked in that date range!");
            }
        }

<<<<<<< HEAD
        // Set bookDate to current date if not provided
        if (booking.getBookDate() == null) {
            booking.setBookDate(LocalDate.now());
        }

        // Set default status if not provided
=======
        // Set bookDate to current date
        booking.setBookDate(LocalDate.now());

        // Set default status
        //Not done
>>>>>>> c23b03cee851e4a31fbf205b0a87f362dada3572
        if (booking.getStatus() == null || booking.getStatus().isBlank()) {
            booking.setStatus("PENDING");
        }

<<<<<<< HEAD
        try {
            Booking saved = bookingRepo.save(booking);
            System.out.println("Booking saved successfully: " + saved);
            return ResponseEntity.ok(saved);
        } catch (Exception e) {
            System.err.println("Error creating booking: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Error creating booking: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateBooking(@PathVariable Integer id, @RequestBody Booking data) {
        System.out.println("Received update request for booking ID " + id + ": " + data);
        
        try {
            Booking existing = bookingRepo.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Booking not found with ID " + id));

            System.out.println("Found existing booking: " + existing);
            
            // Update fields if provided, otherwise keep existing values
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

            System.out.println("Updated booking (to be saved): " + existing);
            
            Booking saved = bookingRepo.save(existing);
            System.out.println("Booking updated successfully: " + saved);
            return ResponseEntity.ok(saved);
        } catch (Exception e) {
            System.err.println("Error updating booking: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Error updating booking: " + e.getMessage());
        }
=======
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
>>>>>>> c23b03cee851e4a31fbf205b0a87f362dada3572
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
