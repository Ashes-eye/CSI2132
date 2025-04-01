package com.example.e_hotel.repository;

import com.example.e_hotel.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    // find by email for login
    Customer findByEmail(String email);
}
