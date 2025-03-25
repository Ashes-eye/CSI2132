package com.example.e_hotel.controller;

import com.example.e_hotel.model.Renting;
import com.example.e_hotel.repository.RentingRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/rentings")
@CrossOrigin(origins = "*")
public class RentingController {

    private final RentingRepository rentingRepository;

    public RentingController(RentingRepository rentingRepository) {
        this.rentingRepository = rentingRepository;
    }

    // GET all rentings
    @GetMapping
    public List<Renting> getAllRentings() {
        return rentingRepository.findAll();
    }

    // GET a renting by ID
    @GetMapping("/{id}")
    public Optional<Renting> getRentingById(@PathVariable int id) {
        return rentingRepository.findById(id);
    }

    // POST create new renting (walk-in or from booking)
    @PostMapping
    public Renting createRenting(@RequestBody Renting renting) {
        return rentingRepository.save(renting);
    }

    // PUT to update renting 
    @PutMapping("/{id}")
    public Renting updateRenting(@PathVariable int id, @RequestBody Renting updated) {
        updated.setRentingID(id);
        return rentingRepository.save(updated);
    }

    // DELETE renting record 
    @DeleteMapping("/{id}")
    public void deleteRenting(@PathVariable int id) {
        rentingRepository.deleteById(id);
    }
}
