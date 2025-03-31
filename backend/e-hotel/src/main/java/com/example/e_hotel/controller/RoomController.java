package com.example.e_hotel.controller;

import com.example.e_hotel.exceptions.ResourceNotFoundException;
import com.example.e_hotel.model.Room;
import com.example.e_hotel.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/room")
@CrossOrigin
public class RoomController {

    @Autowired
    private RoomRepository roomRepo;

    @GetMapping
    public List<Room> getAllRooms() {
        return roomRepo.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Room> getRoomById(@PathVariable Integer id) {
        Room room = roomRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Room not found with ID " + id));
        return ResponseEntity.ok(room);
    }

    @PostMapping
    public ResponseEntity<Room> createRoom(@RequestBody Room room) {
        Room saved = roomRepo.save(room);
        return ResponseEntity.ok(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Room> updateRoom(@PathVariable Integer id, @RequestBody Room data) {
        Room existing = roomRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Room not found with ID " + id));

        existing.setHotelId(data.getHotelId());
        existing.setPrice(data.getPrice());
        existing.setCapacity(data.getCapacity());
        existing.setAmenities(data.getAmenities());
        existing.setSeaView(data.isSeaView());
        existing.setMountainView(data.isMountainView());
        existing.setExtendable(data.isExtendable());
        existing.setProblems(data.getProblems());

        Room saved = roomRepo.save(existing);
        return ResponseEntity.ok(saved);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRoom(@PathVariable Integer id) {
        if (!roomRepo.existsById(id)) {
            throw new ResourceNotFoundException("Room not found with ID " + id);
        }
        roomRepo.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
