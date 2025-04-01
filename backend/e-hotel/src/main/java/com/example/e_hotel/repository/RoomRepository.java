package com.example.e_hotel.repository;

import com.example.e_hotel.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, Integer> {


    List<Room> findByHotelId(Integer hotelId);
}
