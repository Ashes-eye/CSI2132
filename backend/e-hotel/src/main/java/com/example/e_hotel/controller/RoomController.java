package com.example.e_hotel.controllerRoom;

import com.example.e_hotel.modelRoom.Room;
import com.example.e_hotel.repositoryRoom.RoomRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/rooms")
@CrossOrigin(origins = "*")
public class RoomController {

    private final RoomRepository roomRepository;

    public RoomController(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    // GET all rooms
    @GetMapping
    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    // GET rooms by hotel ID
    @GetMapping("/hotel/{hotelID}")
    public List<Room> getRoomsByHotel(@PathVariable int hotelID) {
        return roomRepository.findByHotelID(hotelID);
    }

    // GET a room by ID
    @GetMapping("/{id}")
    public Optional<Room> getRoomById(@PathVariable int id) {
        return roomRepository.findById(id);
    }

    // POST (create) a new room
    @PostMapping
    public Room createRoom(@RequestBody Room room) {
        return roomRepository.save(room);
    }

    // PUT (update) a room
    @PutMapping("/{id}")
    public Room updateRoom(@PathVariable int id, @RequestBody Room updatedRoom) {
        updatedRoom.setRoomID(id);
        return roomRepository.save(updatedRoom);
    }

    // DELETE a room
    @DeleteMapping("/{id}")
    public void deleteRoom(@PathVariable int id) {
        roomRepository.deleteById(id);
    }
}
