package com.example.e_hotel.repository;

import com.example.e_hotel.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {
    // find bookings for a specific room
    List<Booking> findByRoomId(Integer roomId);

    // find bookings for a specific customer
    List<Booking> findByCustomerId(Integer customerId);


}
