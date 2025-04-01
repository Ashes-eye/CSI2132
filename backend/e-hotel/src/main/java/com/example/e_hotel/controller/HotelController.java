package com.example.e_hotel.controller;

import com.example.e_hotel.exceptions.ResourceNotFoundException;
import com.example.e_hotel.model.Hotel;
import com.example.e_hotel.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hotel")
@CrossOrigin
public class HotelController {

    @Autowired
    private HotelRepository hotelRepo;

    @GetMapping
    public List<Hotel> getAllHotels() {
        return hotelRepo.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Hotel> getHotelById(@PathVariable Integer id) {
        Hotel h = hotelRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Hotel not found with ID " + id));
        return ResponseEntity.ok(h);
    }

    @PostMapping
    public ResponseEntity<Hotel> createHotel(@RequestBody Hotel newHotel) {
        Hotel saved = hotelRepo.save(newHotel);
        return ResponseEntity.ok(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Hotel> updateHotel(@PathVariable Integer id, @RequestBody Hotel data) {
        Hotel existing = hotelRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Hotel not found with ID " + id));

        existing.setHotelChainId(data.getHotelChainId());
        existing.setEmail(data.getEmail());
        existing.setPhoneNumber(data.getPhoneNumber());
        existing.setAddress(data.getAddress());
        existing.setNumberOfRooms(data.getNumberOfRooms());
        existing.setRating(data.getRating());

        Hotel saved = hotelRepo.save(existing);
        return ResponseEntity.ok(saved);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteHotel(@PathVariable Integer id) {
        if (!hotelRepo.existsById(id)) {
            throw new ResourceNotFoundException("Hotel not found with ID " + id);
        }
        hotelRepo.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
