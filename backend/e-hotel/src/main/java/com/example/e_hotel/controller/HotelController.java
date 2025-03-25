package com.example.e_hotel.controller;

import com.example.e_hotel.model.Hotel;
import com.example.e_hotel.repository.HotelRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/hotels")
@CrossOrigin(origins = "*")
public class HotelController {

    private final HotelRepository hotelRepository;

    public HotelController(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    // GET all hotels
    @GetMapping
    public List<Hotel> getAllHotels() {
        return hotelRepository.findAll();
    }

    // GET a specific hotel by ID
    @GetMapping("/{id}")
    public Optional<Hotel> getHotelById(@PathVariable int id) {
        return hotelRepository.findById(id);
    }

    // POST a new hotel
    @PostMapping
    public Hotel createHotel(@RequestBody Hotel hotel) {
        return hotelRepository.save(hotel);
    }

    // PUT to update a hotel
    @PutMapping("/{id}")
    public Hotel updateHotel(@PathVariable int id, @RequestBody Hotel updatedHotel) {
        updatedHotel.setHotelID(id);
        return hotelRepository.save(updatedHotel);
    }

    // DELETE a hotel
    @DeleteMapping("/{id}")
    public void deleteHotel(@PathVariable int id) {
        hotelRepository.deleteById(id);
    }
}
