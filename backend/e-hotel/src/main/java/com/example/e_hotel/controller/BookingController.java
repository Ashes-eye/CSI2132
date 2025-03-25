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
        this.rentingRepository= rentingRepository;
    }

    @GetMapping
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Booking> getBookingById(@PathVariable int id) {
        return bookingRepository.findById(id);
    }

    @PostMapping
    public Booking createBooking(@RequestBody Booking booking) {
        return bookingRepository.save(booking);
    }

    @PutMapping("/{id}")
    public Booking updateBooking(@PathVariable int id, @RequestBody Booking updatedBooking) {
        updatedBooking.setBookingID(id);
        return bookingRepository.save(updatedBooking);
    }

    @DeleteMapping("/{id}")
    public void deleteBooking(@PathVariable int id) {
        bookingRepository.deleteById(id);
    }

    @PostMapping("/{id}/checkin")
    public Renting checkInFromBooking(@PathVariable int id, @RequestBody CheckInRequest checkInRequest) {
        Optional<Booking> bookingOpt = bookingRepository.findById(id);
        if (bookingOpt.isEmpty()) {
            throw new RuntimeException("Booking not found with ID: " + id);
        }

        Booking booking = bookingOpt.get();

        Renting renting = new Renting();
        renting.setCustomerID(booking.getCustomerID());
        renting.setRoomID(booking.getRoomID());
        renting.setEmployeeID(checkInRequest.getEmployeeID());
        renting.setPaymentAmount(checkInRequest.getPaymentAmount());

        renting.setCheckInDate(checkInRequest.getCheckInDate() != null ?
                checkInRequest.getCheckInDate() : booking.getCheckInDate());

        renting.setCheckOutDate(checkInRequest.getCheckOutDate() != null ?
                checkInRequest.getCheckOutDate() : booking.getCheckOutDate());

        Renting savedRenting = rentingRepository.save(renting);

        // remove booking after check-in
        bookingRepository.deleteById(id);

        return savedRenting;
    }

    
}
