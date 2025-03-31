package com.example.e_hotel.repository;

import com.example.e_hotel.model.Renting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface RentingRepository extends JpaRepository<Renting, Integer> {

    List<Renting> findByRoomId(Integer roomId);
}
